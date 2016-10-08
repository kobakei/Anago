package io.github.kobakei.anago.viewmodel.base;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;

import com.trello.rxlifecycle.LifecycleTransformer;
import com.trello.rxlifecycle.android.RxLifecycleAndroid;

/**
 * ビューモデルのベースクラス
 * 対応するビュー（Activity/Fragment/View）をコンストラクタで受け取ります。
 * これはActivity Contextへのアクセスと、RxLifecycleのLifecycleTransformerの取得に使うためです。
 * こうすることで、RxJavaのObservableをsubscribeする際に、ビューのライフサイクルに応じて自動的に非同期処理が中断されます。
 *
 * Created by keisuke on 2016/09/18.
 */

public abstract class CustomViewViewModel {

    private final View view;

    public CustomViewViewModel(View view) {
        this.view = view;
        this.view.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() {
            @Override
            public void onViewAttachedToWindow(View view) {

            }

            @Override
            public void onViewDetachedFromWindow(View view) {
                onDetached();
            }
        });
    }

    @NonNull
    public Context getContext() {
        if (view != null) {
            return view.getContext();
        }
        throw new IllegalStateException("No view attached");
    }

    @NonNull
    public final <T> LifecycleTransformer<T> bindToLifecycle() {
        if (view != null) {
            return RxLifecycleAndroid.bindView(view);
        }
        throw new IllegalStateException("No view attached");
    }

    public abstract void onDetached();
}
