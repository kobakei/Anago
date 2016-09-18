package io.github.kobakei.anago.fragment;

import android.support.v4.app.Fragment;

import io.github.kobakei.anago.AnagoApplication;
import io.github.kobakei.anago.di.ActivityComponent;
import io.github.kobakei.anago.di.ActivityModule;

/**
 * Created by keisuke on 2016/09/19.
 */

public abstract class BaseFragment extends Fragment {

    protected ActivityComponent getInjector() {
        AnagoApplication application = (AnagoApplication) getContext().getApplicationContext();
        return application.getInjector().activityComponent(new ActivityModule(getActivity()));
    }

}
