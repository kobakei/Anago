package io.github.kobakei.anago.net;

import io.github.kobakei.anago.entity.AuthToken;
import io.github.kobakei.anago.net.body.AuthorizationBody;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import rx.Single;

/**
 * Created by keisuke on 2016/09/18.
 */

public interface GitHubService {

    @PUT("/authorizations/clients/{client_id}/{fingerprint}")
    Single<AuthToken> putAuthorization(@Header("Authorization") String authorization,
                                       @Path("client_id") String clientId,
                                       @Path("fingerprint") String fingerprint,
                                       @Body AuthorizationBody body);
}
