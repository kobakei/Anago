package io.github.kobakei.anago;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.squareup.leakcanary.LeakCanary;

import io.github.kobakei.anago.di.AppComponent;
import io.github.kobakei.anago.di.AppModule;
import io.github.kobakei.anago.di.DaggerAppComponent;
import timber.log.Timber;

/**
 * Created by keisuke on 2016/09/18.
 */

public class AnagoApplication extends Application {

    private AppComponent injector;

    @Override
    public void onCreate() {
        super.onCreate();

        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);

        injector = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();

        Timber.plant(new Timber.DebugTree());

        Stetho.initializeWithDefaults(this);
    }

    public AppComponent getInjector() {
        return injector;
    }
}
