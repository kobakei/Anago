package io.github.kobakei.anago.viewmodel;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.view.View;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

import io.github.kobakei.anago.activity.BaseActivity;
import io.github.kobakei.anago.entity.User;
import io.github.kobakei.anago.service.CountIntentService;
import io.github.kobakei.anago.usecase.GetUserUseCase;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * ユーザー情報画面のビューモデル
 * Created by keisuke on 2016/09/19.
 */

public class UserViewModel extends ViewModel {

    private final GetUserUseCase getUserUseCase;

    public ObservableField<User> user;
    public ObservableBoolean isConnecting;

    private String paramName;

    @Inject
    public UserViewModel(BaseActivity activity, GetUserUseCase getUserUseCase) {
        super(activity);
        this.getUserUseCase = getUserUseCase;

        this.user = new ObservableField<>();
        this.isConnecting = new ObservableBoolean(true);
    }

    public void setParams(String name) {
        this.paramName = name;
    }

    @Override
    public void onResume() {
        super.onResume();

        getUserUseCase.run(paramName)
                .compose(bindToLifecycle().forSingle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(user1 -> {
                    user.set(user1);

                    EventBus.getDefault().post(new RefreshUserEvent(user.get()));

                    isConnecting.set(false);

                }, Throwable::printStackTrace);
    }

    public static class RefreshUserEvent {
        public final User user;
        public RefreshUserEvent(User user) {
            this.user = user;
        }
    }
}
