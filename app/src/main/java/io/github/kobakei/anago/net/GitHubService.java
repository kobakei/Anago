package io.github.kobakei.anago.net;

import java.util.List;

import io.github.kobakei.anago.entity.AuthToken;
import io.github.kobakei.anago.entity.Repo;
import io.github.kobakei.anago.entity.User;
import io.github.kobakei.anago.net.body.AuthorizationBody;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import rx.Completable;
import rx.Single;

/**
 * GitHub API client
 * Created by keisuke on 2016/09/18.
 */

public interface GitHubService {

    @PUT("/authorizations/clients/{client_id}/{fingerprint}")
    Single<AuthToken> putAuthorization(@Header("Authorization") String authorization,
                                       @Path("client_id") String clientId,
                                       @Path("fingerprint") String fingerprint,
                                       @Body AuthorizationBody body);

    @DELETE("/authorizations/{id}")
    Single<Void> deleteAuthorization(@Path("id") long id);

    @GET("/user/repos")
    Single<List<Repo>> getUserRepos(@Header("Authorization") String authorization);

    @GET("/user/starred/{user}/{repo}")
    Single<Void> getStar(@Header("Authorization") String authorization,
                         @Path("user") String user,
                         @Path("repo") String repo);

    @Headers({"Content-Length: 0"})
    @PUT("/user/starred/{user}/{repo}")
    Single<Void> putStar(@Header("Authorization") String authorization,
                         @Path("user") String user,
                         @Path("repo") String repo);

    @DELETE("/user/starred/{user}/{repo}")
    Single<Void> deleteStar(@Header("Authorization") String authorization,
                            @Path("user") String user,
                            @Path("repo") String repo);

    @GET("/users/{user}")
    Single<User> getUser(@Path("user") String user);
}
