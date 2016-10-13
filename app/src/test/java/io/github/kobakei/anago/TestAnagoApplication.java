package io.github.kobakei.anago;

import org.greenrobot.eventbus.EventBus;
import org.mockito.Mockito;

import io.github.kobakei.anago.di.AppComponent;
import io.github.kobakei.anago.di.AppModule;
import io.github.kobakei.anago.di.DaggerAppComponent;
import io.github.kobakei.anago.di.EventBusModule;

/**
 * テスト用アプリケーションクラス
 * Created by keisuke on 2016/09/24.
 */

public class TestAnagoApplication extends AnagoApplication {

    @Override
    protected AppComponent buildAppComponent() {
        return DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .eventBusModule(new EventBusModule(EventBus.getDefault()))
                .build();
    }

    @Override
    public boolean isUnitTest() {
        return true;
    }
}
