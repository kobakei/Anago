package io.github.kobakei.anago.viewmodel;

import android.app.Activity;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableBoolean;

import javax.inject.Inject;

import io.github.kobakei.anago.activity.RepoActivity;
import io.github.kobakei.anago.entity.Repo;
import io.github.kobakei.anago.usecase.GetUserReposUseCase;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * リポジトリ一覧画面ビューモデル
 * Created by keisuke on 2016/09/18.
 */

public class RepoListViewModel extends ActivityViewModel {

    private final GetUserReposUseCase getUserReposUseCase;

    public ObservableArrayList<Repo> repos;
    public ObservableBoolean isConnecting;

    @Inject
    public RepoListViewModel(Activity activity, GetUserReposUseCase getUserReposUseCase) {
        super(activity);

        this.getUserReposUseCase = getUserReposUseCase;

        repos = new ObservableArrayList<>();
        isConnecting = new ObservableBoolean(true);

        getUserReposUseCase.run()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(repos1 -> {
                    repos.clear();
                    repos.addAll(repos1);
                    isConnecting.set(false);
                });
    }

    public void onItemClick(int position) {
        RepoActivity.startActivity(getActivity(), repos.get(position).id);
    }
}
