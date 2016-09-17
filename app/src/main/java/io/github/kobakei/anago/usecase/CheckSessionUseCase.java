package io.github.kobakei.anago.usecase;

import javax.inject.Inject;

import io.github.kobakei.anago.entity.AuthToken;
import io.github.kobakei.anago.repository.AuthTokenRepository;
import rx.Single;

/**
 * Created by keisuke on 2016/09/18.
 */

public class CheckSessionUseCase {

    private final AuthTokenRepository authTokenRepository;

    @Inject
    public CheckSessionUseCase(AuthTokenRepository authTokenRepository) {
        this.authTokenRepository = authTokenRepository;
    }

    public Single<AuthToken> run() {
        return authTokenRepository.get();
    }
}
