package io.github.kobakei.anago.net;

import java.util.List;

import io.github.kobakei.anago.entity.AuthToken;
import io.github.kobakei.anago.entity.Content;
import io.github.kobakei.anago.entity.Issue;
import io.github.kobakei.anago.entity.PullRequest;
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
import retrofit2.http.Query;
import rx.Single;

/**
 * GitHub API client
 * Created by keisuke on 2016/09/18.
 */

public interface GitHubApiClient {

    @PUT("/authorizations/clients/{client_id}/{fingerprint}")
    Single<AuthToken> putAuthorization(@Header("Authorization") String authorization,
                                       @Path("client_id") String clientId,
                                       @Path("fingerprint") String fingerprint,
                                       @Body AuthorizationBody body);

    @DELETE("/applications/{client_id}/tokens/{token}")
    Single<Void> revokeAuthorization(@Header("Authorization") String authorization,
                                     @Path("client_id") String clientId,
                                     @Path("token") String token);

    @GET("/repositories")
    Single<List<Repo>> getPublicRepos(@Query("page") int page,
                                      @Query("per_page") int perPage);

    @GET("/user/repos")
    Single<List<Repo>> getUserRepos(@Header("Authorization") String authorization,
                                    @Query("page") int page,
                                    @Query("per_page") int perPage);
    @GET("/repos/{user}/{repo}")
    Single<Repo> getRepo(@Path("user") String user,
                         @Path("repo") String repo);

    @GET("/user/starred/{user}/{repo}")
    Single<Void> getStar(@Header("Authorization") String authorization,
                         @Path("user") String user,
                         @Path("repo") String repo);

    @GET("/user/starred")
    Single<List<Repo>> getStarredRepos(@Header("Authorization") String authorization,
                                       @Query("page") int page,
                                       @Query("per_page") int perPage);

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

    @GET("/repos/{user}/{repo}/stargazers")
    Single<List<User>> getStargazers(@Path("user") String user,
                                     @Path("repo") String repo,
                                     @Query("page") int page,
                                     @Query("per_page") int perPage);

    @GET("/repos/{user}/{repo}/contents/{path}")
    Single<Content> getFileContent(@Path("user") String user,
                                   @Path("repo") String repo,
                                   @Path("path") String path);

    @GET("/repos/{user}/{repo}/contents/{path}")
    Single<List<Content>> getDirContent(@Path("user") String user,
                                        @Path("repo") String repo,
                                        @Path("path") String path);

    @GET("/repos/{user}/{repo}/issues")
    Single<List<Issue>> getIssues(@Path("user") String user,
                                  @Path("repo") String repo,
                                  @Query("state") String state);

    @GET("/repos/{user}/{repo}/pulls")
    Single<List<PullRequest>> getPullRequests(@Path("user") String user,
                                              @Path("repo") String repo);
}
