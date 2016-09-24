package io.github.kobakei.anago.usecase;

import javax.inject.Inject;

import io.github.kobakei.anago.repository.RepoRepository;
import io.github.kobakei.anago.repository.StarRepository;
import rx.Completable;

/**
 * スターをつけるユースケース
 * Created by keisuke on 2016/09/18.
 */

public class StarUseCase {

    private final StarRepository starRepository;
    private final RepoRepository repoRepository;

    @Inject
    public StarUseCase(StarRepository starRepository, RepoRepository repoRepository) {
        this.starRepository = starRepository;
        this.repoRepository = repoRepository;
    }

    public Completable run(String user, String repo) {
        return starRepository.put(user, repo)
                .doOnCompleted(repoRepository::clearCache);
    }
}
