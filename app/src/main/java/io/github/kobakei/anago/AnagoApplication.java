package io.github.kobakei.anago;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.squareup.leakcanary.LeakCanary;

import org.greenrobot.eventbus.EventBus;

import io.github.kobakei.anago.di.AppComponent;
import io.github.kobakei.anago.di.AppModule;
import io.github.kobakei.anago.di.DaggerAppComponent;
import io.github.kobakei.anago.di.EventBusModule;
import timber.log.Timber;

/**
 * アプリケーションクラス
 * Created by keisuke on 2016/09/18.
 */

public class AnagoApplication extends Application {

    private AppComponent injector;

    @Override
    public void onCreate() {
        super.onCreate();

        if (!isUnitTest()) {
            if (LeakCanary.isInAnalyzerProcess(this)) {
                return;
            }
            LeakCanary.install(this);

            Stetho.initializeWithDefaults(this);

            Timber.plant(new Timber.DebugTree());
        }

        injector = buildAppComponent();
    }

    /**
     * 単体テストでこのメソッドをオーバーライドしてください
     * @return
     */
    protected AppComponent buildAppComponent() {
        return DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .eventBusModule(new EventBusModule(EventBus.getDefault()))
                .build();
    }

    public AppComponent getInjector() {
        return injector;
    }

    public boolean isUnitTest() {
        return false;
    }
}
