package io.github.kobakei.anago.viewmodel;

import android.databinding.ObservableArrayList;

import javax.inject.Inject;

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

    public ObservableArrayList<Issue> issues;

    private String user;
    private String repo;

    @Inject
    public IssueListFragmentViewModel(BaseFragment fragment, GetIssuesUseCase getIssuesUseCase) {
        super(fragment);
        this.getIssuesUseCase = getIssuesUseCase;
        this.issues = new ObservableArrayList<>();
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onResume() {
        getIssuesUseCase.run(user, repo)
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
