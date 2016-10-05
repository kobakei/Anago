package io.github.kobakei.anago.usecase;

import java.util.List;

import javax.inject.Inject;

import io.github.kobakei.anago.entity.User;
import io.github.kobakei.anago.repository.UserRepository;
import rx.Single;

/**
 * リポジトリのスターした人一覧を取得するユースケース
 * Created by keisuke on 2016/09/20.
 */

public class GetStargazersUseCase {

    private final UserRepository userRepository;

    @Inject
    public GetStargazersUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Single<List<User>> run(String user, String repo, int page, int perPage) {
        return userRepository.getStargazers(user, repo, page, perPage);
    }
}
