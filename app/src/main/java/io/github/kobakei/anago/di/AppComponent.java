package io.github.kobakei.anago.di;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by keisuke on 2016/09/18.
 */
@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {
    ActivityComponent activityComponent(ActivityModule activityModule);
}
