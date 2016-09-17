package io.github.kobakei.anago.repository;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.github.kobakei.anago.dao.AuthTokenDao;
import io.github.kobakei.anago.entity.Repo;
import io.github.kobakei.anago.net.GitHubService;
import rx.Single;

/**
 * リポジトリのリポジトリ
 * Created by keisuke on 2016/09/18.
 */
@Singleton
public class RepoRepository {

    private final GitHubService gitHubService;
    private final AuthTokenDao authTokenDao;

    @Inject
    public RepoRepository(GitHubService gitHubService, AuthTokenDao authTokenDao) {
        this.gitHubService = gitHubService;
        this.authTokenDao = authTokenDao;
    }

    public Single<List<Repo>> getUserRepos() {
        return authTokenDao.get()
                .flatMap(authToken -> {
                    String header = "token " + authToken.token;
                    return gitHubService.getUserRepos(header);
                });
    }
}
