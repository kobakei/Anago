package io.github.kobakei.anago.activity;

import android.support.v7.app.AppCompatActivity;

import io.github.kobakei.anago.AnagoApplication;
import io.github.kobakei.anago.di.ActivityComponent;
import io.github.kobakei.anago.di.ActivityModule;
import io.github.kobakei.anago.di.AppComponent;

/**
 * Created by keisuke on 2016/09/18.
 */

public class BaseActivity extends AppCompatActivity {

    protected ActivityComponent getInjector() {
        AnagoApplication application = (AnagoApplication) getApplication();
        return application.getInjector().activityComponent(new ActivityModule(this));
    }

}
