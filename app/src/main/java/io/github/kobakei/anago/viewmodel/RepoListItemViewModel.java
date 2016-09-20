package io.github.kobakei.anago.viewmodel;

import android.content.Context;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.view.View;
import android.widget.Toast;

import javax.inject.Inject;

import io.github.kobakei.anago.activity.UserActivity;
import io.github.kobakei.anago.entity.Repo;
import io.github.kobakei.anago.usecase.StarUseCase;
import io.github.kobakei.anago.usecase.UnstarUseCase;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * リポジトリ一覧アイテムのビューモデル
 * Created by keisuke on 2016/09/18.
 */

public class RepoListItemViewModel extends ListItemViewModel {

    private final StarUseCase starUseCase;
    private final UnstarUseCase unstarUseCase;

    public ObservableField<Repo> repo;
    public ObservableBoolean starred;

    @Inject
    public RepoListItemViewModel(Context context,
                                 StarUseCase starUseCase,
                                 UnstarUseCase unstarUseCase) {
        super(context);
        this.starUseCase = starUseCase;
        this.unstarUseCase = unstarUseCase;

        this.repo = new ObservableField<>();
        this.starred = new ObservableBoolean(false);
    }

    public void onImageClick(View view) {
        UserActivity.startActivity(getContext(), repo.get().owner.login);
    }

    public void onStarClick(View view) {
        if (starred.get()) {
            unstarUseCase.run(repo.get().owner.login, repo.get().name)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(() -> {
                        starred.set(true);
                        Toast.makeText(getContext(), "Unstarred!", Toast.LENGTH_SHORT).show();
                    });
        } else {
            starUseCase.run(repo.get().owner.login, repo.get().name)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(() -> {
                        starred.set(true);
                        Toast.makeText(getContext(), "Starred!", Toast.LENGTH_SHORT).show();
                    });
        }
    }
}
