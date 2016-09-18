package io.github.kobakei.anago.usecase;

import javax.inject.Inject;

import io.github.kobakei.anago.repository.StarRepository;
import rx.Completable;

/**
 * Created by keisuke on 2016/09/18.
 */

public class UnstarUseCase {

    private final StarRepository starRepository;

    @Inject
    public UnstarUseCase(StarRepository starRepository) {
        this.starRepository = starRepository;
    }

    public Completable run(String user, String repo) {
        return starRepository.delete(user, repo);
    }
}
