package io.github.kobakei.anago.repository;

import android.content.Context;

import java.util.ArrayList;
import java.util.UUID;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.github.kobakei.anago.R;
import io.github.kobakei.anago.dao.AuthTokenDao;
import io.github.kobakei.anago.entity.AuthToken;
import io.github.kobakei.anago.net.GitHubService;
import io.github.kobakei.anago.net.body.AuthorizationBody;
import io.github.kobakei.anago.util.NetUtil;
import rx.Single;
import timber.log.Timber;

/**
 * Created by keisuke on 2016/09/18.
 */
@Singleton
public class AuthTokenRepository {

    private Context context;
    private final GitHubService gitHubService;
    private final AuthTokenDao authTokenDao;

    @Inject
    public AuthTokenRepository(Context context, GitHubService gitHubService,
                               AuthTokenDao authTokenDao) {
        this.context = context;
        this.gitHubService = gitHubService;
        this.authTokenDao = authTokenDao;
    }

    public Single<AuthToken> getOrCreate(String name, String password) {
        String header = NetUtil.getBasicHeader(name, password);
        String clientId = context.getString(R.string.github_client_id);
        String fingerprint = UUID.randomUUID().toString();
        AuthorizationBody body = new AuthorizationBody();
        body.client_secret = context.getString(R.string.github_client_secret);
        body.scopes = new ArrayList<>();
        body.scopes.add("public_repo");
        return this.gitHubService.putAuthorization(header, clientId, fingerprint, body)
                .doOnSuccess(authToken -> {
                    long id = authTokenDao.upsert(authToken).toBlocking().value();
                    Timber.v("Upserted ID = " + id);
                });
    }

    public Single<AuthToken> get() {
        return this.authTokenDao.get();
    }
}
