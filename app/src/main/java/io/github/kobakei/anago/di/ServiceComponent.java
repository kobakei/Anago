package io.github.kobakei.anago.di;

import dagger.Subcomponent;
import io.github.kobakei.anago.activity.HomeActivity;
import io.github.kobakei.anago.activity.RepoActivity;
import io.github.kobakei.anago.activity.SignInActivity;
import io.github.kobakei.anago.activity.StargazerListActivity;
import io.github.kobakei.anago.activity.UserActivity;
import io.github.kobakei.anago.service.CountIntentService;
import io.github.kobakei.anago.viewmodel.StargazerListItemViewModel;

/**
 * Created by keisuke on 2016/09/18.
 */
@Subcomponent(modules = {ServiceModule.class})
public interface ServiceComponent {
    void inject(CountIntentService service);
}
