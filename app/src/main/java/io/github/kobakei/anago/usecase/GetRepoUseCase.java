package io.github.kobakei.anago.usecase;

import javax.inject.Inject;

import io.github.kobakei.anago.entity.Repo;
import io.github.kobakei.anago.repository.RepoRepository;
import rx.Single;

/**
 * リポジトリ情報を取得するユースケース
 * Created by keisuke on 2016/09/18.
 */

public class GetRepoUseCase {

    private final RepoRepository repoRepository;

    @Inject
    public GetRepoUseCase(RepoRepository repoRepository) {
        this.repoRepository = repoRepository;
    }

    public Single<Repo> run(String user, String repo) {
        return repoRepository.getByFullname(user, repo);
    }
}
