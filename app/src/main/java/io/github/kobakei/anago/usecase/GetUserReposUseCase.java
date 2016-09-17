package io.github.kobakei.anago.usecase;

import java.util.List;

import javax.inject.Inject;

import io.github.kobakei.anago.entity.Repo;
import io.github.kobakei.anago.repository.RepoRepository;
import rx.Single;

/**
 * Created by keisuke on 2016/09/18.
 */

public class GetUserReposUseCase {

    private final RepoRepository repoRepository;

    @Inject
    public GetUserReposUseCase(RepoRepository repoRepository) {
        this.repoRepository = repoRepository;
    }

    public Single<List<Repo>> run() {
        return repoRepository.getUserRepos();
    }
}
