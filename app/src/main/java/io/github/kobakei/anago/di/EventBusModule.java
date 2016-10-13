package io.github.kobakei.anago.di;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * イベントバスのファクトリ
 * 単体テストで差し替えれるように専用のDIモジュールにしています
 * Created by keisuke on 2016/10/13.
 */
@Module
public class EventBusModule {

    private final EventBus eventBus;

    public EventBusModule(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    @Provides
    @Singleton
    public EventBus provideEventBus() {
        return eventBus;
    }

}
