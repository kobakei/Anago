package io.github.kobakei.anago.viewmodel;

import android.app.Activity;
import android.databinding.ObservableArrayList;

import javax.inject.Inject;

import io.github.kobakei.anago.entity.User;
import io.github.kobakei.anago.usecase.GetStargazersUseCase;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * スターした人一覧画面のビューモデル
 * Created by keisuke on 2016/09/20.
 */

public class StargazerListViewModel extends ActivityViewModel {

    private final GetStargazersUseCase getStargazersUseCase;

    public ObservableArrayList<User> users;

    private String paramUser;
    private String paramRepo;

    @Inject
    public StargazerListViewModel(Activity activity, GetStargazersUseCase getStargazersUseCase) {
        super(activity);
        this.getStargazersUseCase = getStargazersUseCase;

        this.users = new ObservableArrayList<>();
    }

    public void setParams(String user, String repo) {
        this.paramUser = user;
        this.paramRepo = repo;
    }

    @Override
    public void onResume() {
        super.onResume();

        getStargazersUseCase.run(paramUser, paramRepo)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(users1 -> {
                    this.users.clear();
                    this.users.addAll(users1);
                });
    }

    @Override
    public void onPause() {
        super.onPause();
    }
}
