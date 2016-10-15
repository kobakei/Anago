package io.github.kobakei.anago.viewmodel;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.view.View;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

import io.github.kobakei.anago.activity.BaseActivity;
import io.github.kobakei.anago.entity.User;
import io.github.kobakei.anago.usecase.GetUserUseCase;
import io.github.kobakei.anago.viewmodel.base.ActivityViewModel;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;

/**
 * ユーザー情報画面のビューモデル
 * Created by keisuke on 2016/09/19.
 */

public class UserActivityViewModel extends ActivityViewModel {

    private final GetUserUseCase getUserUseCase;
    private final EventBus eventBus;

    public ObservableField<User> user;
    public ObservableBoolean isConnecting;

    private String paramName;

    @Inject
    public UserActivityViewModel(BaseActivity activity, GetUserUseCase getUserUseCase, EventBus eventBus) {
        super(activity);
        this.getUserUseCase = getUserUseCase;
        this.eventBus = eventBus;

        this.user = new ObservableField<>();
        this.isConnecting = new ObservableBoolean(true);
    }

    public void setParams(String name) {
        this.paramName = name;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onResume() {
        getUserUseCase.run(paramName)
                .compose(bindToLifecycle().forSingle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(user1 -> {
                    user.set(user1);
                    eventBus.post(new RefreshUserEvent(user.get()));
                    isConnecting.set(false);
                }, throwable -> {
                    Timber.e(throwable);
                    isConnecting.set(false);
                });
    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {

    }

    public void onNavigationClick(View view) {
        getActivity().finish();
    }

    public static class RefreshUserEvent {
        public final User user;
        public RefreshUserEvent(User user) {
            this.user = user;
        }
    }
}
