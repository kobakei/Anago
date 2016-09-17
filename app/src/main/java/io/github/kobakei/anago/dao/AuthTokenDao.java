package io.github.kobakei.anago.dao;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.github.kobakei.anago.entity.AuthToken;
import io.github.kobakei.anago.entity.OrmaDatabase;
import rx.Single;

/**
 * Created by keisuke on 2016/09/18.
 */
@Singleton
public class AuthTokenDao {

    private final OrmaDatabase ormaDatabase;

    @Inject
    public AuthTokenDao(OrmaDatabase ormaDatabase) {
        this.ormaDatabase = ormaDatabase;
    }

    public Single<Long> upsert(AuthToken authToken) {
        return ormaDatabase.relationOfAuthToken()
                .upserter()
                .executeAsObservable(authToken);
    }
}
