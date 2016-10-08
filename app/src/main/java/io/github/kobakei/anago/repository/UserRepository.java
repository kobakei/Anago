package io.github.kobakei.anago.repository;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.github.kobakei.anago.entity.User;
import io.github.kobakei.anago.net.GitHubApiClient;
import rx.Single;

/**
 * ユーザーのリポジトリ
 * Created by keisuke on 2016/09/19.
 */
@Singleton
public class UserRepository extends Repository<String, User> {

    private final GitHubApiClient gitHubApiClient;

    @Inject
    public UserRepository(GitHubApiClient gitHubApiClient) {
        super();
        this.gitHubApiClient = gitHubApiClient;
    }

    public Single<User> get(String name) {
        return gitHubApiClient.getUser(name);
    }

    public Single<List<User>> getStargazers(String user, String repo, int page, int perPage) {
        return gitHubApiClient.getStargazers(user, repo, page, perPage);
    }
}
