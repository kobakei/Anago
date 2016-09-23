package io.github.kobakei.anago.activity;

import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import io.github.kobakei.anago.AnagoApplication;
import io.github.kobakei.anago.di.ActivityComponent;
import io.github.kobakei.anago.di.ActivityModule;

/**
 * Activityのベースクラス
 * Created by keisuke on 2016/09/18.
 */

public class BaseActivity extends RxAppCompatActivity {

    protected ActivityComponent getInjector() {
        AnagoApplication application = (AnagoApplication) getApplication();
        return application.getInjector().activityComponent(new ActivityModule(this));
    }

}
