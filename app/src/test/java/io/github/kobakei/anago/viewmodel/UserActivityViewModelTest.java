package io.github.kobakei.anago.viewmodel;

import android.os.Build;

import org.greenrobot.eventbus.EventBus;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import io.github.kobakei.anago.BuildConfig;
import io.github.kobakei.anago.TestAnagoApplication;
import io.github.kobakei.anago.activity.BaseActivity;
import io.github.kobakei.anago.activity.UserActivity;
import io.github.kobakei.anago.entity.User;
import io.github.kobakei.anago.usecase.GetUserUseCase;
import rx.Single;
import rx.plugins.RxJavaHooks;
import rx.schedulers.Schedulers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * ビューモデルのテストの例
 * Created by keisuke on 2016/10/13.
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP,
        application = TestAnagoApplication.class)
public class UserActivityViewModelTest {

    UserActivityViewModel userViewModel;
    EventBus eventBus;

    @Before
    public void setUp() {
        BaseActivity activity = Robolectric.setupActivity(UserActivity.class);

        // ユースケースのモック
        User user = new User();
        user.id = 1L;
        user.login = "user";
        GetUserUseCase getUserUseCase = mock(GetUserUseCase.class);
        when(getUserUseCase.run("user")).thenReturn(Single.just(user));
        when(getUserUseCase.run("wrong")).thenReturn(Single.error(new Throwable("error")));

        // イベントバスのモック
        eventBus = mock(EventBus.class);

        // ビューモデルの作成
        userViewModel = new UserActivityViewModel(activity, getUserUseCase, eventBus);

        // subscribeOnのスレッドをioからimmediateに変更
        RxJavaHooks.setOnIOScheduler(scheduler -> Schedulers.immediate());
    }

    @Test
    public void onResume_isSuccess() {
        userViewModel.setParams("user");
        userViewModel.onResume();
        Assert.assertEquals("user", userViewModel.user.get().login);
        Assert.assertFalse(userViewModel.isConnecting.get());
        verify(eventBus).post(any(UserActivityViewModel.RefreshUserEvent.class));
    }

    @Test
    public void onResume_isFailure() {
        userViewModel.setParams("wrong");
        userViewModel.onResume();
        Assert.assertNull(userViewModel.user.get());
        Assert.assertFalse(userViewModel.isConnecting.get());
    }
}
