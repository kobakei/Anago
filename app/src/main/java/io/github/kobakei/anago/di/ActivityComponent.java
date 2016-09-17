package io.github.kobakei.anago.di;

import dagger.Component;
import dagger.Subcomponent;
import io.github.kobakei.anago.activity.SignInActivity;

/**
 * Created by keisuke on 2016/09/18.
 */
@Subcomponent(modules = {ActivityModule.class})
public interface ActivityComponent {
    void inject(SignInActivity activity);
}
