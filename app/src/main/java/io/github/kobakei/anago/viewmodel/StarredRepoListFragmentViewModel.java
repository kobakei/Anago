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
import io.github.kobakei.anago.usecase.GetStarredReposUseCase;
import io.github.kobakei.anago.viewmodel.base.FragmentViewModel;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;

/**
 * パブリックリポジトリ一覧画面ビューモデル
 * Created by keisuke on 2016/09/18.
 */

public class StarredRepoListFragmentViewModel extends FragmentViewModel {

    private final GetStarredReposUseCase getStarredReposUseCase;
    private final EventBus eventBus;

    public ObservableArrayList<Pair<Repo, Boolean>> repos;
    public ObservableBoolean isConnecting;
    public ObservableBoolean isRefreshing;

    @Inject
    public StarredRepoListFragmentViewModel(BaseFragment fragment, GetStarredReposUseCase getStarredReposUseCase,
                                            EventBus eventBus) {
        super(fragment);

        this.getStarredReposUseCase = getStarredReposUseCase;
        this.eventBus = eventBus;

        repos = new ObservableArrayList<>();
        isConnecting = new ObservableBoolean(true);
        isRefreshing = new ObservableBoolean(false);
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onResume() {
        eventBus.register(this);

        refreshData();
    }

    @Override
    public void onPause() {
        eventBus.unregister(this);
    }

    @Override
    public void onStop() {

    }

    public void onRefresh() {
        Timber.v("onRefresh");
        this.isRefreshing.set(true);
        refreshData();
    }

    public void onItemClick(int position) {
        Repo repo = repos.get(position).first;
        RepoActivity.startActivity(getContext(), repo.owner.login, repo.name);
    }

    @Subscribe
    public void onStarUpdate(RepoListItemViewModel.StarEvent event) {
        refreshData();
    }

    private void refreshData() {
        getStarredReposUseCase.run()
                .compose(bindToLifecycle().forSingle())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(repos1 -> {
                    this.isRefreshing.set(false);
                    repos.clear();
                    repos.addAll(repos1);
                    isConnecting.set(false);
                }, Throwable::printStackTrace);
    }
}
