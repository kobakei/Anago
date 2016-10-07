package io.github.kobakei.anago.di;

import android.app.Service;

import dagger.Module;
import dagger.Provides;
import io.github.kobakei.anago.activity.BaseActivity;

/**
 * Created by keisuke on 2016/09/18.
 */
@Module
public class ServiceModule {

    private final Service service;

    public ServiceModule(Service service) {
        this.service = service;
    }

    @Provides
    public Service provideService() {
        return service;
    }
}
