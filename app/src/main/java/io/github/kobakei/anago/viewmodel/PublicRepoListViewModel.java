package io.github.kobakei.anago.viewmodel;

import android.app.Activity;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableBoolean;
import android.util.Pair;

import javax.inject.Inject;

import io.github.kobakei.anago.activity.RepoActivity;
import io.github.kobakei.anago.entity.Repo;
import io.github.kobakei.anago.usecase.GetPublicReposUseCase;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;

/**
 * パブリックリポジトリ一覧画面ビューモデル
 * Created by keisuke on 2016/09/18.
 */

public class PublicRepoListViewModel extends ActivityViewModel {

    private final GetPublicReposUseCase getPublicReposUseCase;

    public ObservableArrayList<Pair<Repo, Boolean>> repos;
    public ObservableBoolean isConnecting;
    public ObservableBoolean isRefreshing;

    @Inject
    public PublicRepoListViewModel(Activity activity, GetPublicReposUseCase getPublicReposUseCase) {
        super(activity);

        this.getPublicReposUseCase = getPublicReposUseCase;

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
        Subscription subscription = getPublicReposUseCase.run()
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
