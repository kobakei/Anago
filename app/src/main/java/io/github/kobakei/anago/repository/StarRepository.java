package io.github.kobakei.anago.repository;

import javax.inject.Inject;

import io.github.kobakei.anago.dao.AuthTokenDao;
import io.github.kobakei.anago.net.GitHubService;
import io.github.kobakei.anago.util.NetUtil;
import rx.Completable;

/**
 * Created by keisuke on 2016/09/18.
 */

public class StarRepository {

    private final GitHubService gitHubService;
    private final AuthTokenDao authTokenDao;

    @Inject
    public StarRepository(GitHubService gitHubService, AuthTokenDao authTokenDao) {
        this.gitHubService = gitHubService;
        this.authTokenDao = authTokenDao;
    }

    public Completable put(String user, String repo) {
        return authTokenDao.get()
                .flatMapCompletable(authToken -> {
                    String header = "token " + authToken.token;
                    return gitHubService.putStar(header, user, repo).toCompletable();
                });
    }
}
