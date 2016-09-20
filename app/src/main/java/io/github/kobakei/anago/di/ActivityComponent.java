package io.github.kobakei.anago.di;

import dagger.Subcomponent;
import io.github.kobakei.anago.activity.RepoActivity;
import io.github.kobakei.anago.activity.HomeActivity;
import io.github.kobakei.anago.activity.SignInActivity;
import io.github.kobakei.anago.activity.StargazerListActivity;
import io.github.kobakei.anago.activity.UserActivity;
import io.github.kobakei.anago.fragment.RepoListFragment;
import io.github.kobakei.anago.viewmodel.RepoListItemViewModel;
import io.github.kobakei.anago.viewmodel.StargazerListItemViewModel;

/**
 * Created by keisuke on 2016/09/18.
 */
@Subcomponent(modules = {ActivityModule.class})
public interface ActivityComponent {
    void inject(SignInActivity activity);
    void inject(HomeActivity activity);
    void inject(RepoActivity activity);
    void inject(UserActivity activity);
    void inject(StargazerListActivity activity);

    void inject(RepoListFragment fragment);

    RepoListItemViewModel repoListItemViewModel();
    StargazerListItemViewModel stargazerListItemViewModel();
}
