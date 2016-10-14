package io.github.kobakei.anago.viewmodel.base;

import android.content.Context;
import android.support.annotation.NonNull;

import com.trello.rxlifecycle.LifecycleTransformer;

import io.github.kobakei.anago.activity.BaseActivity;

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

public abstract class ActivityViewModel {

    private final BaseActivity activity;

    public ActivityViewModel(BaseActivity activity) {
        this.activity = activity;
    }

    @NonNull
    public BaseActivity getActivity() {
        if (activity != null) {
            return activity;
        }
        throw new IllegalStateException("No view attached");
    }

    @NonNull
    public Context getContext() {
        if (activity != null) {
            return activity;
        }
        throw new IllegalStateException("No view attached");
    }

    @NonNull
    public final <T> LifecycleTransformer<T> bindToLifecycle() {
        if (activity != null) {
            return activity.bindToLifecycle();
        }
        throw new IllegalStateException("No view attached");
    }

    public abstract void onStart();

    public abstract void onResume();

    public abstract void onPause();

    public abstract void onStop();
}
