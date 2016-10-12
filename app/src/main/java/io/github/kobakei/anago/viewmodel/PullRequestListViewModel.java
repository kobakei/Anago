package io.github.kobakei.anago.viewmodel;

import android.databinding.ObservableArrayList;

import javax.inject.Inject;

import io.github.kobakei.anago.entity.Issue;
import io.github.kobakei.anago.entity.PullRequest;
import io.github.kobakei.anago.fragment.BaseFragment;
import io.github.kobakei.anago.usecase.GetIssuesUseCase;
import io.github.kobakei.anago.usecase.GetPullRequestsUseCase;
import io.github.kobakei.anago.viewmodel.base.FragmentViewModel;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by keisuke on 2016/10/09.
 */

public class PullRequestListViewModel extends FragmentViewModel {

    private final GetPullRequestsUseCase getPullRequestsUseCase;

    public ObservableArrayList<PullRequest> pullRequests;

    private String user;
    private String repo;

    @Inject
    public PullRequestListViewModel(BaseFragment fragment, GetPullRequestsUseCase getPullRequestsUseCase) {
        super(fragment);
        this.getPullRequestsUseCase = getPullRequestsUseCase;
        this.pullRequests = new ObservableArrayList<>();
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onResume() {
        getPullRequestsUseCase.run(user, repo)
                .compose(bindToLifecycle().forSingle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(pullRequests -> {
                    this.pullRequests.clear();
                    this.pullRequests.addAll(pullRequests);
                }, throwable -> {
                    Timber.e(throwable);
                });
    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {

    }

    public void setRepo(String user, String repo) {
        this.user = user;
        this.repo = repo;
    }
}
