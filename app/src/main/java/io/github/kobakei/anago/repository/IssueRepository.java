package io.github.kobakei.anago.repository;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.github.kobakei.anago.entity.Issue;
import io.github.kobakei.anago.net.GitHubApiClient;
import rx.Single;

/**
 * Created by keisuke on 2016/10/09.
 */
@Singleton
public class IssueRepository {

    private final GitHubApiClient gitHubApiClient;

    @Inject
    public IssueRepository(GitHubApiClient gitHubApiClient) {
        this.gitHubApiClient = gitHubApiClient;
    }

    public Single<List<Issue>> getAllByRepo(String user, String repo) {
        return gitHubApiClient.getIssues(user, repo);
    }
}
