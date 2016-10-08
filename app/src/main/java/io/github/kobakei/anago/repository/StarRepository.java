package io.github.kobakei.anago.repository;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.github.kobakei.anago.dao.AuthTokenDao;
import io.github.kobakei.anago.net.GitHubApiClient;
import rx.Completable;
import rx.Single;
import timber.log.Timber;

/**
 * スターのリポジトリ
 * Created by keisuke on 2016/09/18.
 */
@Singleton
public class StarRepository extends Repository<String, Boolean> {

    private final GitHubApiClient gitHubApiClient;
    private final AuthTokenDao authTokenDao;

    @Inject
    public StarRepository(GitHubApiClient gitHubApiClient, AuthTokenDao authTokenDao) {
        super();
        this.gitHubApiClient = gitHubApiClient;
        this.authTokenDao = authTokenDao;
    }

    public Single<Boolean> get(String user, String repo) {
        String key = user + "/" + repo;
        if (getCache().containsKey(key)) {
            return Single.just(getCache().get(key));
        }
        return authTokenDao.get()
                .flatMap(authToken -> {
                    String header = "token " + authToken.token;
                    return gitHubApiClient.getStar(header, user, repo)
                            .toCompletable()
                            .toSingleDefault(true)
                            .onErrorReturn(throwable -> false);
                })
                .doOnSuccess(aBoolean -> getCache().put(key, aBoolean));
    }

    public Completable put(String user, String repo) {
        return authTokenDao.get()
                .flatMapCompletable(authToken -> {
                    String header = "token " + authToken.token;
                    return gitHubApiClient.putStar(header, user, repo).toCompletable();
                })
                .doOnCompleted(() -> {
                    Timber.v("put complete");
                    getCache().put(user + "/" + repo, true);
                });
    }

    public Completable delete(String user, String repo) {
        return authTokenDao.get()
                .flatMapCompletable(authToken -> {
                    String header = "token " + authToken.token;
                    return gitHubApiClient.deleteStar(header, user, repo).toCompletable();
                })
                .doOnCompleted(() -> {
                    Timber.v("delete complete");
                    getCache().put(user + "/" + repo, false);
                });
    }
}
