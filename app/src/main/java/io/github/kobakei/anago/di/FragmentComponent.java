package io.github.kobakei.anago.di;

import dagger.Subcomponent;
import io.github.kobakei.anago.activity.HomeActivity;
import io.github.kobakei.anago.activity.RepoActivity;
import io.github.kobakei.anago.activity.SignInActivity;
import io.github.kobakei.anago.activity.StargazerListActivity;
import io.github.kobakei.anago.activity.UserActivity;
import io.github.kobakei.anago.fragment.IssueListFragment;
import io.github.kobakei.anago.fragment.MyRepoListFragment;
import io.github.kobakei.anago.fragment.RepoInfoFragment;
import io.github.kobakei.anago.fragment.StarredRepoListFragment;
import io.github.kobakei.anago.viewmodel.RepoListItemViewModel;
import io.github.kobakei.anago.viewmodel.StargazerListItemViewModel;

/**
 * Created by keisuke on 2016/09/18.
 */
@Subcomponent(modules = {FragmentModule.class})
public interface FragmentComponent {
    void inject(MyRepoListFragment fragment);
    void inject(StarredRepoListFragment fragment);
    void inject(RepoInfoFragment fragment);
    void inject(IssueListFragment fragment);

    RepoListItemViewModel repoListItemViewModel();
}
