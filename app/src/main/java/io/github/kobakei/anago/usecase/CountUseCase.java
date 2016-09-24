package io.github.kobakei.anago.usecase;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import rx.Observable;
import rx.schedulers.Schedulers;

/**
 * Created by keisuke on 2016/09/24.
 */

public class CountUseCase {

    @Inject
    public CountUseCase() {

    }

    public Observable<Integer> run() {
        return Observable.range(1, 30).delay(1, TimeUnit.SECONDS, Schedulers.trampoline());
    }
}
