package io.github.kobakei.anago.usecase;

import java.util.List;

import javax.inject.Inject;

import io.github.kobakei.anago.entity.Issue;
import io.github.kobakei.anago.entity.PullRequest;
import io.github.kobakei.anago.repository.IssueRepository;
import io.github.kobakei.anago.repository.PullRequestRepository;
import rx.Single;

/**
 * Created by keisuke on 2016/10/09.
 */

public class GetPullRequestsUseCase {

    private final PullRequestRepository pullRequestRepository;

    @Inject
    public GetPullRequestsUseCase(PullRequestRepository pullRequestRepository) {
        this.pullRequestRepository = pullRequestRepository;
    }

    public Single<List<PullRequest>> run(String user, String repo) {
        return pullRequestRepository.getAllByRepo(user, repo);
    }
}
