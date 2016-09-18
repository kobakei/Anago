package io.github.kobakei.anago.usecase;

import javax.inject.Inject;

import io.github.kobakei.anago.entity.User;
import io.github.kobakei.anago.repository.UserRepository;
import rx.Single;

/**
 * Created by keisuke on 2016/09/19.
 */

public class GetUserUseCase {

    private final UserRepository userRepository;

    @Inject
    public GetUserUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Single<User> run(String name) {
        return userRepository.get(name);
    }
}
