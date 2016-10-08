package io.github.kobakei.anago.viewmodel;

import android.view.View;
import android.widget.Toast;

import javax.inject.Inject;

import io.github.kobakei.anago.service.CountIntentService;
import io.github.kobakei.anago.usecase.CountUseCase;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;

/**
 * デバッグビューのビューモデル
 * カスタムビューのビューモデルの例
 * Created by keisuke on 2016/10/08.
 */

public class MyCustomViewModel extends ViewModel {

    private final CountUseCase countUseCase;

    @Inject
    public MyCustomViewModel(View view, CountUseCase countUseCase) {
        super(view);
        this.countUseCase = countUseCase;
    }

    public void onTestClick(View view) {
        countUseCase.run()
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
