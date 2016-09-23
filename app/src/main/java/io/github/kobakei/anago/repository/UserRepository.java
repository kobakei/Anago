package io.github.kobakei.anago.repository;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.github.kobakei.anago.entity.User;
import io.github.kobakei.anago.net.GitHubService;
import rx.Single;

/**
 * ユーザーのリポジトリ
 * Created by keisuke on 2016/09/19.
 */
@Singleton
public class UserRepository {

    private final GitHubService gitHubService;

    @Inject
    public UserRepository(GitHubService gitHubService) {
        this.gitHubService = gitHubService;
    }

    public Single<User> get(String name) {
        return gitHubService.getUser(name);
    }

    public Single<List<User>> getStargazers(String user, String repo, int page, int perPage) {
        return gitHubService.getStargazers(user, repo, page, perPage);
    }
}
