package io.github.kobakei.anago.viewmodel;

import javax.inject.Inject;

import io.github.kobakei.anago.fragment.BaseFragment;
import io.github.kobakei.anago.usecase.GetIssuesUseCase;
import io.github.kobakei.anago.viewmodel.base.FragmentViewModel;

/**
 * Created by keisuke on 2016/10/09.
 */

public class IssueListViewModel extends FragmentViewModel {

    GetIssuesUseCase getIssuesUseCase;

    @Inject
    public IssueListViewModel(BaseFragment fragment, GetIssuesUseCase getIssuesUseCase) {
        super(fragment);
        this.getIssuesUseCase = getIssuesUseCase;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {

    }
}
