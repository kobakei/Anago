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
public class RepoRepository extends Repository<String, Repo> {

    private final GitHubService gitHubService;
    private final AuthTokenDao authTokenDao;

    @Inject
    public RepoRepository(GitHubService gitHubService, AuthTokenDao authTokenDao) {
        super();
        this.gitHubService = gitHubService;
        this.authTokenDao = authTokenDao;
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
                        getCache().put(repo.full_name, repo);
                    }
                });
    }

    public Single<List<Repo>> getStarredRepos(int page, int perPage) {
        return authTokenDao.get()
                .flatMap(authToken -> {
                    String header = "token " + authToken.token;
                    return gitHubService.getStarredRepos(header, page, perPage);
                })
                .doOnSuccess(repos1 -> {
                    for (Repo repo : repos1) {
                        getCache().put(repo.full_name, repo);
                    }
                });
    }

    public Single<Repo> getByFullname(String user, String repo) {
        String fullname = user + "/" + repo;
        if (getCache().containsKey(fullname)) {
            return Single.just(getCache().get(fullname));
        }
        return gitHubService.getRepo(user, repo);
    }
}
