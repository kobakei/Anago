package io.github.kobakei.anago.di;

import dagger.Module;
import dagger.Provides;
import io.github.kobakei.anago.activity.BaseActivity;

/**
 * Created by keisuke on 2016/09/18.
 */
@Module
public class ActivityModule {

    private final BaseActivity activity;

    public ActivityModule(BaseActivity activity) {
        this.activity = activity;
    }

    @Provides
    public BaseActivity provideActivity() {
        return activity;
    }

}
