package io.github.kobakei.anago.viewmodel;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableBoolean;
import android.util.Pair;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import javax.inject.Inject;

import io.github.kobakei.anago.activity.RepoActivity;
import io.github.kobakei.anago.entity.Repo;
import io.github.kobakei.anago.fragment.BaseFragment;
import io.github.kobakei.anago.usecase.GetUserReposUseCase;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;

/**
 * 自分のリポジトリ一覧画面ビューモデル
 * Created by keisuke on 2016/09/18.
 */

public class MyRepoListViewModel extends FragmentViewModel {

    private final GetUserReposUseCase getUserReposUseCase;

    public ObservableArrayList<Pair<Repo, Boolean>> repos;
    public ObservableBoolean isConnecting;
    public ObservableBoolean isRefreshing;

    @Inject
    public MyRepoListViewModel(BaseFragment fragment, GetUserReposUseCase getUserReposUseCase) {
        super(fragment);

        this.getUserReposUseCase = getUserReposUseCase;

        repos = new ObservableArrayList<>();
        isConnecting = new ObservableBoolean(true);
        isRefreshing = new ObservableBoolean(false);
    }

    @Override
    public void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);

        refreshData();
    }

    @Override
    public void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }

    public void onRefresh() {
        Timber.v("onRefresh");
        this.isRefreshing.set(true);
        refreshData();
    }

    public void onItemClick(int position) {
        Repo repo = repos.get(position).first;
        RepoActivity.startActivity(getFragment().getActivity(), repo.owner.login, repo.name);
    }

    @Subscribe
    public void onStarUpdate(RepoListItemViewModel.StarEvent event) {
        refreshData();
    }

    private void refreshData() {
        getUserReposUseCase.run()
                .compose(getFragment().bindToLifecycle().forSingle())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(repos1 -> {
                    this.isRefreshing.set(false);
                    repos.clear();
                    repos.addAll(repos1);
                    isConnecting.set(false);
                });
    }
}
