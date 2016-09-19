package io.github.kobakei.anago.usecase;

import javax.inject.Inject;

import io.github.kobakei.anago.repository.AuthTokenRepository;
import rx.Completable;

/**
 * サインアウトのユースケース
 * Created by keisuke on 2016/09/19.
 */

public class SignOutUseCase {

    private final AuthTokenRepository authTokenRepository;

    @Inject
    public SignOutUseCase(AuthTokenRepository authTokenRepository) {
        this.authTokenRepository = authTokenRepository;
    }

    public Completable run() {
        return authTokenRepository.delete();
    }
}
