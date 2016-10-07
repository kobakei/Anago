package io.github.kobakei.anago.viewmodel;

import android.view.View;
import android.widget.Toast;

import javax.inject.Inject;

import io.github.kobakei.anago.service.CountIntentService;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;

/**
 * デバッグビューのビューモデル
 * カスタムビューのビューモデルの例
 * Created by keisuke on 2016/10/08.
 */

public class DebugViewModel extends ViewModel {

    @Inject
    public DebugViewModel(View view) {
        super(view);
    }

    public void onTestClick(View view) {
        Observable.just(10)
                .flatMap(integer -> {
                    try {
                        Thread.sleep(10 * 1000L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return Observable.just(integer);
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(bindToLifecycle())
                .subscribe(integer -> {
                    Toast.makeText(getContext(), "Int: " + integer, Toast.LENGTH_SHORT).show();
                }, throwable -> {
                    Timber.e(throwable);
                    Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
                }, () -> {
                    Toast.makeText(getContext(), "Complete", Toast.LENGTH_SHORT).show();
                });
    }

    public void onTest2Click(View view) {
        CountIntentService.startService(getContext());
    }
}
