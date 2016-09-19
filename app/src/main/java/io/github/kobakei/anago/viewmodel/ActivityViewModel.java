package io.github.kobakei.anago.viewmodel;

import android.app.Activity;

import rx.subscriptions.CompositeSubscription;

/**
 * Created by keisuke on 2016/09/18.
 */

public abstract class ActivityViewModel {

    private final Activity activity;
    private final CompositeSubscription compositeSubscription;

    public ActivityViewModel(Activity activity) {
        this.activity = activity;
        this.compositeSubscription = new CompositeSubscription();
    }

    public Activity getActivity() {
        return activity;
    }

    public CompositeSubscription getCompositeSubscription() {
        return compositeSubscription;
    }

    public void onResume() {

    }

    public void onPause() {
        compositeSubscription.unsubscribe();
    }
}
