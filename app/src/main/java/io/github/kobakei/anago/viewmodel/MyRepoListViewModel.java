package io.github.kobakei.anago.viewmodel;

import android.app.Activity;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableBoolean;
import android.util.Pair;

import javax.inject.Inject;

import io.github.kobakei.anago.activity.RepoActivity;
import io.github.kobakei.anago.entity.Repo;
import io.github.kobakei.anago.usecase.GetUserReposUseCase;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;

/**
 * 自分のリポジトリ一覧画面ビューモデル
 * Created by keisuke on 2016/09/18.
 */

public class MyRepoListViewModel extends ActivityViewModel {

    private final GetUserReposUseCase getUserReposUseCase;

    public ObservableArrayList<Pair<Repo, Boolean>> repos;
    public ObservableBoolean isConnecting;
    public ObservableBoolean isRefreshing;

    @Inject
    public MyRepoListViewModel(Activity activity, GetUserReposUseCase getUserReposUseCase) {
        super(activity);

        this.getUserReposUseCase = getUserReposUseCase;

        repos = new ObservableArrayList<>();
        isConnecting = new ObservableBoolean(true);
        isRefreshing = new ObservableBoolean(false);

        refreshData();
    }

    public void onRefresh() {
        Timber.v("onRefresh");
        this.isRefreshing.set(true);
        refreshData();
    }

    public void onItemClick(int position) {
        Repo repo = repos.get(position).first;
        RepoActivity.startActivity(getActivity(), repo.owner.login, repo.name);
    }

    private void refreshData() {
        Subscription subscription = getUserReposUseCase.run()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(repos1 -> {
                    this.isRefreshing.set(false);
                    repos.clear();
                    repos.addAll(repos1);
                    isConnecting.set(false);
                });
        getCompositeSubscription().add(subscription);
    }
}
