package io.github.kobakei.anago.usecase;

import android.util.Pair;

import java.util.List;

import javax.inject.Inject;

import io.github.kobakei.anago.entity.Repo;
import io.github.kobakei.anago.repository.RepoRepository;
import rx.Observable;
import rx.Single;

/**
 * 自分のリポジトリ一覧を取得するユースケース
 * Created by keisuke on 2016/09/18.
 */

public class GetUserReposUseCase {

    private final RepoRepository repoRepository;

    @Inject
    public GetUserReposUseCase(RepoRepository repoRepository) {
        this.repoRepository = repoRepository;
    }

    public Single<List<Pair<Repo, Boolean>>> run() {
        return repoRepository.getUserRepos()
                .flatMapObservable(Observable::from)
                .map(repo -> new Pair<>(repo, true)) // TODO APIでスターを取得
                .toList()
                .toSingle();
    }
}
