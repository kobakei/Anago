package io.github.kobakei.anago.usecase;

import android.util.Pair;

import java.util.List;

import javax.inject.Inject;

import io.github.kobakei.anago.entity.Repo;
import io.github.kobakei.anago.repository.RepoRepository;
import io.github.kobakei.anago.repository.StarRepository;
import rx.Observable;
import rx.Single;

/**
 * パブリックなリポジトリ一覧を取得するユースケース
 * Created by keisuke on 2016/09/18.
 */

public class GetPublicReposUseCase {

    private final RepoRepository repoRepository;
    private final StarRepository starRepository;

    @Inject
    public GetPublicReposUseCase(RepoRepository repoRepository, StarRepository starRepository) {
        this.repoRepository = repoRepository;
        this.starRepository = starRepository;
    }

    public Single<List<Pair<Repo, Boolean>>> run() {
        return repoRepository.getPublicRepos()
                .flatMapObservable(Observable::from)
                .flatMap(repo -> Observable.combineLatest(
                        Observable.just(repo),
                        starRepository.get(repo.owner.login, repo.name)
                                .toSingleDefault(true).onErrorReturn(throwable -> false).toObservable(),
                        Pair::create
                ))
                .toList()
                .toSingle();
    }
}
