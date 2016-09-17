package io.github.kobakei.anago.viewmodel;

import android.app.Activity;

/**
 * Created by keisuke on 2016/09/18.
 */

public abstract class ActivityViewModel {

    private final Activity activity;

    public ActivityViewModel(Activity activity) {
        this.activity = activity;
    }

    public Activity getActivity() {
        return activity;
    }
}
