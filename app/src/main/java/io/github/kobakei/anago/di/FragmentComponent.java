package io.github.kobakei.anago.di;

import dagger.Subcomponent;
import io.github.kobakei.anago.fragment.IssueListFragment;
import io.github.kobakei.anago.fragment.MyRepoListFragment;
import io.github.kobakei.anago.fragment.PullRequestListFragment;
import io.github.kobakei.anago.fragment.RepoInfoFragment;
import io.github.kobakei.anago.fragment.StarredRepoListFragment;
import io.github.kobakei.anago.viewmodel.IssueListItemViewModel;
import io.github.kobakei.anago.viewmodel.PullRequestListItemViewModel;
import io.github.kobakei.anago.viewmodel.RepoListItemViewModel;

/**
 * Created by keisuke on 2016/09/18.
 */
@Subcomponent(modules = {FragmentModule.class})
public interface FragmentComponent {
    void inject(MyRepoListFragment fragment);
    void inject(StarredRepoListFragment fragment);
    void inject(RepoInfoFragment fragment);
    void inject(IssueListFragment fragment);
    void inject(PullRequestListFragment fragment);

    RepoListItemViewModel repoListItemViewModel();
    IssueListItemViewModel issueListItemViewModel();
    PullRequestListItemViewModel pullRequestListItemViewModel();
}
