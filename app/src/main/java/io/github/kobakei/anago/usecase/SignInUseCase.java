package io.github.kobakei.anago.usecase;

import javax.inject.Inject;

import io.github.kobakei.anago.entity.AuthToken;
import io.github.kobakei.anago.repository.AuthTokenRepository;
import rx.Single;

/**
 * Created by keisuke on 2016/09/18.
 */

public class SignInUseCase {

    private final AuthTokenRepository authTokenRepository;

    @Inject
    public SignInUseCase(AuthTokenRepository authTokenRepository) {
        this.authTokenRepository = authTokenRepository;
    }

    public Single<AuthToken> run(String name, String password) {
        return authTokenRepository.getOrCreate(name, password);
    }
}
