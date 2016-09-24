package io.github.kobakei.anago.repository;

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
public class StarRepository extends Repository<String, Boolean> {

    private final GitHubService gitHubService;
    private final AuthTokenDao authTokenDao;

    @Inject
    public StarRepository(GitHubService gitHubService, AuthTokenDao authTokenDao) {
        super();
        this.gitHubService = gitHubService;
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
                    return gitHubService.getStar(header, user, repo)
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
                    return gitHubService.putStar(header, user, repo).toCompletable();
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
                    return gitHubService.deleteStar(header, user, repo).toCompletable();
                })
                .doOnCompleted(() -> {
                    Timber.v("delete complete");
                    getCache().put(user + "/" + repo, false);
                });
    }
}
