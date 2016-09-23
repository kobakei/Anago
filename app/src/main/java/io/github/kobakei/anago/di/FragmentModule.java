package io.github.kobakei.anago.di;

import dagger.Module;
import dagger.Provides;
import io.github.kobakei.anago.activity.BaseActivity;
import io.github.kobakei.anago.fragment.BaseFragment;

/**
 * Created by keisuke on 2016/09/18.
 */
@Module
public class FragmentModule {

    private final BaseFragment fragment;

    public FragmentModule(BaseFragment fragment) {
        this.fragment = fragment;
    }

    @Provides
    public BaseFragment provideFragment() {
        return fragment;
    }

}
