package io.github.kobakei.anago.viewmodel;

import io.github.kobakei.anago.fragment.BaseFragment;

/**
 * Fragment用ビューモデルのベースクラス
 * Created by keisuke on 2016/09/18.
 */

public abstract class FragmentViewModel {

    private final BaseFragment fragment;

    public FragmentViewModel(BaseFragment fragment) {
        this.fragment = fragment;
    }

    public BaseFragment getFragment() {
        return fragment;
    }

    public void onResume() {

    }

    public void onPause() {
    }
}
