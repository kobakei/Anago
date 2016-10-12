package io.github.kobakei.anago.repository;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.github.kobakei.anago.entity.PullRequest;
import io.github.kobakei.anago.net.GitHubApiClient;
import rx.Single;

/**
 * Created by keisuke on 2016/10/09.
 */
@Singleton
public class PullRequestRepository extends Repository<String, PullRequest> {

    private final GitHubApiClient gitHubApiClient;

    @Inject
    public PullRequestRepository(GitHubApiClient gitHubApiClient) {
        super();
        this.gitHubApiClient = gitHubApiClient;
    }

    public Single<List<PullRequest>> getAllByRepo(String user, String repo) {
        return gitHubApiClient.getPullRequests(user, repo);
    }
}
