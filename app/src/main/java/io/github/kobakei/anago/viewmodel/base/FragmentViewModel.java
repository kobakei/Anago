package io.github.kobakei.anago.viewmodel.base;

import android.content.Context;
import android.support.annotation.NonNull;

import com.trello.rxlifecycle.LifecycleTransformer;

import io.github.kobakei.anago.fragment.BaseFragment;

/**
 * ビューモデルのベースクラス
 * 対応するビュー（Activity/Fragment/View）をコンストラクタで受け取ります。
 * これはActivity Contextへのアクセスと、RxLifecycleのLifecycleTransformerの取得に使うためです。
 * こうすることで、RxJavaのObservableをsubscribeする際に、ビューのライフサイクルに応じて自動的に非同期処理が中断されます。
 *
 * Created by keisuke on 2016/09/18.
 */

public abstract class FragmentViewModel {

    private final BaseFragment fragment;

    public FragmentViewModel(BaseFragment fragment) {
        this.fragment = fragment;
    }

    @Deprecated
    public BaseFragment getFragment() {
        return fragment;
    }

    @NonNull
    public Context getContext() {
        if (fragment != null) {
            return fragment.getContext();
        }
        throw new IllegalStateException("No view attached");
    }

    @NonNull
    public final <T> LifecycleTransformer<T> bindToLifecycle() {
        if (fragment != null) {
            return fragment.bindToLifecycle();
        }
        throw new IllegalStateException("No view attached");
    }

    public abstract void onStart();

    public abstract void onResume();

    public abstract void onPause();

    public abstract void onStop();
}
