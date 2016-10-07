package io.github.kobakei.anago.di;

import android.view.View;

import dagger.Module;
import dagger.Provides;

/**
 * Created by keisuke on 2016/09/18.
 */
@Module
public class ViewModule {

    private final View view;

    public ViewModule(View view) {
        this.view = view;
    }

    @Provides
    public View provideView() {
        return view;
    }
}
