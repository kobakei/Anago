package io.github.kobakei.anago.viewmodel;

import io.github.kobakei.anago.activity.BaseActivity;

/**
 * Activity用ビューモデルのベースクラス
 * Created by keisuke on 2016/09/18.
 */

public abstract class ActivityViewModel {

    private final BaseActivity activity;

    public ActivityViewModel(BaseActivity activity) {
        this.activity = activity;
    }

    public BaseActivity getActivity() {
        return activity;
    }

    public void onResume() {

    }

    public void onPause() {
    }
}
