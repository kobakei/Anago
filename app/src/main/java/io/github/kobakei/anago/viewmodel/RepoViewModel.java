package io.github.kobakei.anago.viewmodel;

import android.app.Activity;
import android.databinding.ObservableField;

import javax.inject.Inject;

import io.github.kobakei.anago.entity.Repo;
import io.github.kobakei.anago.usecase.GetRepoUseCase;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * リポジトリ詳細画面のビューモデル
 * Created by keisuke on 2016/09/18.
 */

public class RepoViewModel extends ActivityViewModel {

    private final GetRepoUseCase getRepoUseCase;

    public ObservableField<Repo> repo;

    @Inject
    public RepoViewModel(Activity activity, GetRepoUseCase getRepoUseCase) {
        super(activity);
        this.getRepoUseCase = getRepoUseCase;

        this.repo = new ObservableField<>();

        long id = getActivity().getIntent().getLongExtra("id", 0L);
        getRepoUseCase.run(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(repo1 -> {
                    repo.set(repo1);
                });
    }
}
