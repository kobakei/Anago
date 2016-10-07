package io.github.kobakei.anago.viewmodel;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;

import com.trello.rxlifecycle.LifecycleTransformer;
import com.trello.rxlifecycle.android.RxLifecycleAndroid;

import io.github.kobakei.anago.activity.BaseActivity;
import io.github.kobakei.anago.fragment.BaseFragment;

/**
 * ビューモデルのベースクラス
 * 対応するビュー（Activity/Fragment/View）をコンストラクタで受け取ります。
 * これはActivity Contextへのアクセスと、RxLifecycleのLifecycleTransformerの取得に使うためです。
 * こうすることで、RxJavaのObservableをsubscribeする際に、ビューのライフサイクルに応じて自動的に非同期処理が中断されます。
 *
 * TODO Activity, Fragment, View用に分割したほうがいいかもしれない
 * TODO ビューモデルが直接ActivityやFragmentを操作するのは望ましくないが、Activity.finishなどは使いたい。getContextとは別に残す？
 *
 * Created by keisuke on 2016/09/18.
 */

public abstract class ViewModel {

    private final BaseActivity activity;
    private final BaseFragment fragment;
    private final View view;

    ViewModel(BaseActivity activity) {
        this.activity = activity;
        this.fragment = null;
        this.view = null;
    }

    ViewModel(BaseFragment fragment) {
        this.activity = null;
        this.fragment = fragment;
        this.view = null;
    }

    ViewModel(View view) {
        this.activity = null;
        this.fragment = null;
        this.view = view;
    }

    @Deprecated
    public BaseFragment getFragment() {
        return fragment;
    }

    @Deprecated
    public BaseActivity getActivity() {
        return activity;
    }

    @NonNull
    public Context getContext() {
        if (activity != null) {
            return activity;
        }
        if (fragment != null) {
            return fragment.getContext();
        }
        if (view != null) {
            return view.getContext();
        }
        throw new IllegalStateException("No view attached");
    }

    @NonNull
    public final <T> LifecycleTransformer<T> bindToLifecycle() {
        if (activity != null) {
            return activity.bindToLifecycle();
        }
        if (fragment != null) {
            return fragment.bindToLifecycle();
        }
        if (view != null) {
            return RxLifecycleAndroid.bindView(view);
        }
        throw new IllegalStateException("No view attached");
    }

    /**
     * Activity / FragmentのonResumeで呼ばれます
     * EventBusのregisterなどはここで呼んでください
     */
    public void onResume() {
        // do nothing
    }

    /**
     * Activity / FragmentのonPauseで呼ばれます
     * EventBusのunregisterなどはここで呼んでください
     */
    public void onPause() {
        // do nothing
    }
}
