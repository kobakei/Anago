package io.github.kobakei.anago.viewmodel;

import android.databinding.ObservableArrayList;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import javax.inject.Inject;

import io.github.kobakei.anago.activity.RepoActivity;
import io.github.kobakei.anago.entity.Issue;
import io.github.kobakei.anago.fragment.BaseFragment;
import io.github.kobakei.anago.usecase.GetIssuesUseCase;
import io.github.kobakei.anago.viewmodel.base.FragmentViewModel;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by keisuke on 2016/10/09.
 */

public class IssueListFragmentViewModel extends FragmentViewModel {

    private final GetIssuesUseCase getIssuesUseCase;
    private final EventBus eventBus;

    public ObservableArrayList<Issue> issues;

    private String user;
    private String repo;

    @Inject
    public IssueListFragmentViewModel(BaseFragment fragment, GetIssuesUseCase getIssuesUseCase,
                                      EventBus eventBus) {
        super(fragment);
        this.getIssuesUseCase = getIssuesUseCase;
        this.eventBus = eventBus;

        this.issues = new ObservableArrayList<>();
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onResume() {
        eventBus.register(this);
        refresh("open");
    }

    @Override
    public void onPause() {
        eventBus.unregister(this);
    }

    @Override
    public void onStop() {

    }

    public void setRepo(String user, String repo) {
        this.user = user;
        this.repo = repo;
    }

    @Subscribe
    public void onShowAllIssuesEvent(RepoActivityViewModel.ShowAllIssuesEvent event) {
        refresh("all");
    }

    private void refresh(String state) {
        getIssuesUseCase.run(user, repo, state)
                .compose(bindToLifecycle().forSingle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(issues -> {
                    this.issues.clear();
                    this.issues.addAll(issues);
                }, throwable -> {
                    Timber.e(throwable);
                });
    }
}
