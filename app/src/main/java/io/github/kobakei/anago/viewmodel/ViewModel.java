package io.github.kobakei.anago.viewmodel;

import io.github.kobakei.anago.activity.BaseActivity;
import io.github.kobakei.anago.fragment.BaseFragment;

/**
 * Activity用ビューモデルのベースクラス
 * Created by keisuke on 2016/09/18.
 */

public abstract class ViewModel {

    private final BaseActivity activity;
    private final BaseFragment fragment;

    ViewModel(BaseActivity activity) {
        this.activity = activity;
        this.fragment = null;
    }

    ViewModel(BaseFragment fragment) {
        this.activity = null;
        this.fragment = fragment;
    }

    public BaseFragment getFragment() {
        return fragment;
    }

    public BaseActivity getActivity() {
        return activity;
    }

    public void onResume() {

    }

    public void onPause() {
    }
}