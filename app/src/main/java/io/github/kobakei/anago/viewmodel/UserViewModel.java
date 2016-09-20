package io.github.kobakei.anago.viewmodel;

import android.app.Activity;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import javax.inject.Inject;

import io.github.kobakei.anago.entity.User;
import io.github.kobakei.anago.usecase.GetUserUseCase;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * ユーザー情報画面のビューモデル
 * Created by keisuke on 2016/09/19.
 */

public class UserViewModel extends ActivityViewModel {

    private final GetUserUseCase getUserUseCase;

    public ObservableField<User> user;
    public ObservableBoolean isConnecting;

    private String paramName;

    @Inject
    public UserViewModel(Activity activity, GetUserUseCase getUserUseCase) {
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

        Subscription subscription = getUserUseCase.run(paramName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(user1 -> {
                    user.set(user1);

                    // TODO ActionBarのタイトルはどこでセットすればいい？
                    AppCompatActivity appCompatActivity = (AppCompatActivity) getActivity();
                    appCompatActivity.getSupportActionBar().setTitle(user.get().login);

                    isConnecting.set(false);

                }, Throwable::printStackTrace);
        getCompositeSubscription().add(subscription);
    }

    public void onTestClick(View view) {
        Subscription subscription = Observable.just(10)
                .flatMap(integer -> {
                    try {
                        Thread.sleep(10 * 1000L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return Observable.just(integer);
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(integer -> {
                    Toast.makeText(getActivity(), "Int: " + integer, Toast.LENGTH_SHORT).show();
                }, throwable -> {
                    Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
                }, () -> {
                    Toast.makeText(getActivity(), "Complete", Toast.LENGTH_SHORT).show();
                });
        getCompositeSubscription().add(subscription);
    }
}
