package io.github.kobakei.anago.usecase;

import java.util.List;

import javax.inject.Inject;

import io.github.kobakei.anago.entity.Issue;
import io.github.kobakei.anago.repository.IssueRepository;
import rx.Single;

/**
 * Created by keisuke on 2016/10/09.
 */

public class GetIssuesUseCase {

    private final IssueRepository issueRepository;

    @Inject
    public GetIssuesUseCase(IssueRepository issueRepository) {
        this.issueRepository = issueRepository;
    }

    public Single<List<Issue>> run(String user, String repo, String state) {
        return issueRepository.getAllByRepo(user, repo, state);
    }
}
