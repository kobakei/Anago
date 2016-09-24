package io.github.kobakei.anago.usecase;

import javax.inject.Inject;

import io.github.kobakei.anago.repository.RepoRepository;
import io.github.kobakei.anago.repository.StarRepository;
import rx.Completable;

/**
 * スターを解除するユースケース
 * Created by keisuke on 2016/09/18.
 */

public class UnstarUseCase {

    private final StarRepository starRepository;
    private final RepoRepository repoRepository;

    @Inject
    public UnstarUseCase(StarRepository starRepository, RepoRepository repoRepository) {
        this.starRepository = starRepository;
        this.repoRepository = repoRepository;
    }

    public Completable run(String user, String repo) {
        return starRepository.delete(user, repo)
                .doOnCompleted(repoRepository::clearCache);
    }
}
