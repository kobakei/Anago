package io.github.kobakei.anago.di;

import dagger.Subcomponent;
import io.github.kobakei.anago.service.CountIntentService;
import io.github.kobakei.anago.view.DebugView;

/**
 * Created by keisuke on 2016/09/18.
 */
@Subcomponent(modules = {ViewModule.class})
public interface ViewComponent {
    void inject(DebugView view);
}
