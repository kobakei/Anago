package io.github.kobakei.anago.viewmodel;

import android.app.Activity;
import android.databinding.ObservableField;
import android.view.View;
import android.widget.Toast;

import javax.inject.Inject;

import io.github.kobakei.anago.entity.Repo;
import io.github.kobakei.anago.usecase.GetRepoUseCase;
import io.github.kobakei.anago.usecase.StarUseCase;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * リポジトリ詳細画面のビューモデル
 * Created by keisuke on 2016/09/18.
 */

public class RepoViewModel extends ActivityViewModel {

    private final GetRepoUseCase getRepoUseCase;
    private final StarUseCase starUseCase;

    public ObservableField<Repo> repo;

    @Inject
    public RepoViewModel(Activity activity, GetRepoUseCase getRepoUseCase,
                         StarUseCase starUseCase) {
        super(activity);
        this.getRepoUseCase = getRepoUseCase;
        this.starUseCase = starUseCase;

        this.repo = new ObservableField<>();

        long id = getActivity().getIntent().getLongExtra("id", 0L);
        getRepoUseCase.run(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(repo1 -> {
                    repo.set(repo1);
                });
    }

    public void onStarClick(View view) {
        this.starUseCase.run(repo.get().owner.login, repo.get().name)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> {
                    Toast.makeText(getActivity(), "Starred!", Toast.LENGTH_SHORT).show();
                }, throwable -> {
                    Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
                });
    }
}
