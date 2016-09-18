package io.github.kobakei.anago.viewmodel;

import android.app.Activity;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.support.v4.util.Pair;
import android.view.View;
import android.widget.Toast;

import javax.inject.Inject;

import io.github.kobakei.anago.entity.Repo;
import io.github.kobakei.anago.usecase.CheckStarUseCase;
import io.github.kobakei.anago.usecase.GetRepoUseCase;
import io.github.kobakei.anago.usecase.StarUseCase;
import io.github.kobakei.anago.usecase.UnstarUseCase;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * リポジトリ詳細画面のビューモデル
 * Created by keisuke on 2016/09/18.
 */

public class RepoViewModel extends ActivityViewModel {

    private final GetRepoUseCase getRepoUseCase;
    private final CheckStarUseCase checkStarUseCase;
    private final StarUseCase starUseCase;
    private final UnstarUseCase unstarUseCase;

    public ObservableField<Repo> repo;
    public ObservableBoolean starred;

    @Inject
    public RepoViewModel(Activity activity, GetRepoUseCase getRepoUseCase,
                         CheckStarUseCase checkStarUseCase, StarUseCase starUseCase,
                         UnstarUseCase unstarUseCase) {
        super(activity);
        this.getRepoUseCase = getRepoUseCase;
        this.checkStarUseCase = checkStarUseCase;
        this.starUseCase = starUseCase;
        this.unstarUseCase = unstarUseCase;

        this.repo = new ObservableField<>();
        this.starred = new ObservableBoolean(false);

        long id = getActivity().getIntent().getLongExtra("id", 0L);
        getRepoUseCase.run(id)
                .flatMapObservable(repo1 -> Observable.combineLatest(
                        Observable.just(repo1),
                        checkStarUseCase.run(repo1.owner.login, repo1.name).toObservable(),
                        Pair::create
                ))
                .toSingle()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(pair -> {
                    this.repo.set(pair.first);
                    this.starred.set(pair.second);
                    if (pair.second) {
                        Toast.makeText(activity, "Starred", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(activity, "Unstarred", Toast.LENGTH_SHORT).show();
                    }
                }, Throwable::printStackTrace);
    }

    public void onStarClick(View view) {
        this.starUseCase.run(repo.get().owner.login, repo.get().name)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> {
                    this.starred.set(true);
                    Toast.makeText(getActivity(), "Starred!", Toast.LENGTH_SHORT).show();
                }, throwable -> {
                    Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
                });
    }

    public void onUnstarClick(View view) {
        this.unstarUseCase.run(repo.get().owner.login, repo.get().name)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> {
                    this.starred.set(false);
                    Toast.makeText(getActivity(), "Unstarred!", Toast.LENGTH_SHORT).show();
                }, throwable -> {
                    Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
                });
    }
}
