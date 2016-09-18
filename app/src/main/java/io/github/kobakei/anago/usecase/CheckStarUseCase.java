package io.github.kobakei.anago.usecase;

import javax.inject.Inject;

import io.github.kobakei.anago.repository.StarRepository;
import rx.Single;

/**
 * スターしているかチェックするユースケース
 * Created by keisuke on 2016/09/18.
 */

public class CheckStarUseCase {

    private final StarRepository starRepository;

    @Inject
    public CheckStarUseCase(StarRepository starRepository) {
        this.starRepository = starRepository;
    }

    public Single<Boolean> run(String user, String repo) {
        return starRepository.get(user, repo)
                .toSingleDefault(true)
                .onErrorReturn(throwable -> false);
    }
}
