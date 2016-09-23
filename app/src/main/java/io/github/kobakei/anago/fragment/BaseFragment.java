package io.github.kobakei.anago.fragment;

import com.trello.rxlifecycle.components.support.RxFragment;

import io.github.kobakei.anago.AnagoApplication;
import io.github.kobakei.anago.di.FragmentComponent;
import io.github.kobakei.anago.di.FragmentModule;

/**
 * Fragmentのベースクラス
 * Created by keisuke on 2016/09/19.
 */

public abstract class BaseFragment extends RxFragment{

    protected FragmentComponent getInjector() {
        AnagoApplication application = (AnagoApplication) getContext().getApplicationContext();
        return application.getInjector().fragmentComponent(new FragmentModule(this));
    }

}
