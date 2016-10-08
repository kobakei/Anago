package io.github.kobakei.anago.usecase;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import rx.Observable;
import rx.schedulers.Schedulers;

/**
 * 15秒カウントするユースケース
 * 時間がかかる処理の例
 * Created by keisuke on 2016/09/24.
 */

public class CountUseCase {

    @Inject
    public CountUseCase() {

    }

    public Observable<Integer> run() {
        return Observable.range(1, 15).delay(1, TimeUnit.SECONDS, Schedulers.trampoline());
    }
}
