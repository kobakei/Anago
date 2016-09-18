package io.github.kobakei.anago.viewmodel;

import android.app.Activity;
import android.databinding.ObservableField;

import javax.inject.Inject;

import io.github.kobakei.anago.entity.User;
import io.github.kobakei.anago.usecase.GetUserUseCase;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * ユーザー情報画面のビューモデル
 * Created by keisuke on 2016/09/19.
 */

public class UserViewModel extends ActivityViewModel {

    private final GetUserUseCase getUserUseCase;

    public ObservableField<User> user;

    @Inject
    public UserViewModel(Activity activity, GetUserUseCase getUserUseCase) {
        super(activity);
        this.getUserUseCase = getUserUseCase;

        this.user = new ObservableField<>();

        String name = activity.getIntent().getStringExtra("name");

        getUserUseCase.run(name)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(user1 -> user.set(user1), Throwable::printStackTrace);
    }
}
