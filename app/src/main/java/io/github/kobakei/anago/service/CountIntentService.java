package io.github.kobakei.anago.service;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import javax.inject.Inject;

import io.github.kobakei.anago.AnagoApplication;
import io.github.kobakei.anago.di.ServiceModule;
import io.github.kobakei.anago.usecase.CountUseCase;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;

/**
 * 時間のかかる処理を実行するサービスの例
 * このサービスは30秒かけて1から30までカウントし、完了時にトースト表示します
 * Created by keisuke on 2016/09/24.
 */

public class CountIntentService extends IntentService {

    @Inject
    CountUseCase countUseCase;

    public CountIntentService() {
        super("CountIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        AnagoApplication application = (AnagoApplication) getApplication();
        application.getInjector().serviceComponent(new ServiceModule(this)).inject(this);

        countUseCase.run()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(integer -> {
                    Timber.v("Count = " + integer);
                }, throwable -> {
                    Timber.e(throwable);
                }, () -> {
                    Toast.makeText(getApplicationContext(), "Complete counting", Toast.LENGTH_SHORT).show();
                });
    }

    public static void startService(Context context) {
        Intent intent = new Intent(context, CountIntentService.class);
        context.startService(intent);
    }
}
