package io.github.kobakei.anago.repository;

import android.support.v4.util.LongSparseArray;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    private Map<String, Repo> cache;

    @Inject
    public RepoRepository(GitHubService gitHubService, AuthTokenDao authTokenDao) {
        this.gitHubService = gitHubService;
        this.authTokenDao = authTokenDao;

        this.cache = new HashMap<>();
    }

    public Single<List<Repo>> getPublicRepos(int page, int perPage) {
        return gitHubService.getPublicRepos(page, perPage);
    }

    public Single<List<Repo>> getUserRepos(int page, int perPage) {
        return authTokenDao.get()
                .flatMap(authToken -> {
                    String header = "token " + authToken.token;
                    return gitHubService.getUserRepos(header, page, perPage);
                })
                .doOnSuccess(repos1 -> {
                    for (Repo repo : repos1) {
                        cache.put(repo.full_name, repo);
                    }
                });
    }

    public Single<Repo> getByFullname(String fullname) {
        return Single.just(cache.get(fullname));
    }
}
