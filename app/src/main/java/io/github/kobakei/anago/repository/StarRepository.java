package io.github.kobakei.anago.repository;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.github.kobakei.anago.dao.AuthTokenDao;
import io.github.kobakei.anago.net.GitHubService;
import rx.Completable;
import rx.Single;
import timber.log.Timber;

/**
 * スターのリポジトリ
 * Created by keisuke on 2016/09/18.
 */
@Singleton
public class StarRepository {

    private final GitHubService gitHubService;
    private final AuthTokenDao authTokenDao;

    private final Map<String, Boolean> cache;

    @Inject
    public StarRepository(GitHubService gitHubService, AuthTokenDao authTokenDao) {
        this.gitHubService = gitHubService;
        this.authTokenDao = authTokenDao;

        this.cache = new HashMap<>();
    }

    public Single<Boolean> get(String user, String repo) {
        String key = user + "/" + repo;
        if (cache.containsKey(key)) {
            return Single.just(cache.get(key));
        }
        return authTokenDao.get()
                .flatMap(authToken -> {
                    String header = "token " + authToken.token;
                    return gitHubService.getStar(header, user, repo)
                            .toCompletable()
                            .toSingleDefault(true)
                            .onErrorReturn(throwable -> false);
                })
                .doOnSuccess(aBoolean -> cache.put(key, aBoolean));
    }

    public Completable put(String user, String repo) {
        return authTokenDao.get()
                .flatMapCompletable(authToken -> {
                    String header = "token " + authToken.token;
                    return gitHubService.putStar(header, user, repo).toCompletable();
                })
                .doOnCompleted(() -> {
                    Timber.v("put complete");
                    cache.put(user + "/" + repo, true);
                });
    }

    public Completable delete(String user, String repo) {
        return authTokenDao.get()
                .flatMapCompletable(authToken -> {
                    String header = "token " + authToken.token;
                    return gitHubService.deleteStar(header, user, repo).toCompletable();
                })
                .doOnCompleted(() -> {
                    Timber.v("delete complete");
                    cache.put(user + "/" + repo, false);
                });
    }
}
