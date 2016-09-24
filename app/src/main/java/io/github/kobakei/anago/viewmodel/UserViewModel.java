package io.github.kobakei.anago.viewmodel;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import javax.inject.Inject;

import io.github.kobakei.anago.activity.BaseActivity;
import io.github.kobakei.anago.entity.User;
import io.github.kobakei.anago.usecase.GetUserUseCase;
import rx.Observable;
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
                .compose(getActivity().bindToLifecycle().forSingle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(user1 -> {
                    user.set(user1);

                    getActivity().getSupportActionBar().setTitle(user.get().login);

                    isConnecting.set(false);

                }, Throwable::printStackTrace);
    }

    public void onTestClick(View view) {
        Observable.just(10)
                .flatMap(integer -> {
                    try {
                        Thread.sleep(10 * 1000L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return Observable.just(integer);
                })
                .compose(getActivity().bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(integer -> {
                    Toast.makeText(getActivity(), "Int: " + integer, Toast.LENGTH_SHORT).show();
                }, throwable -> {
                    Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
                }, () -> {
                    Toast.makeText(getActivity(), "Complete", Toast.LENGTH_SHORT).show();
                });
    }
}
