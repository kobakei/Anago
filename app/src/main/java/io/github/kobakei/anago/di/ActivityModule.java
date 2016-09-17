package io.github.kobakei.anago.di;

import android.app.Activity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by keisuke on 2016/09/18.
 */
@Module
public class ActivityModule {

    private final Activity activity;

    public ActivityModule(Activity activity) {
        this.activity = activity;
    }

    @Provides
    public Activity provideActivity() {
        return activity;
    }

}
