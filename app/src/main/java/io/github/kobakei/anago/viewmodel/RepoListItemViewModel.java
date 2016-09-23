package io.github.kobakei.anago.viewmodel;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.view.View;
import android.widget.Toast;

import javax.inject.Inject;

import io.github.kobakei.anago.activity.UserActivity;
import io.github.kobakei.anago.entity.Repo;
import io.github.kobakei.anago.fragment.BaseFragment;
import io.github.kobakei.anago.usecase.StarUseCase;
import io.github.kobakei.anago.usecase.UnstarUseCase;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * リポジトリ一覧アイテムのビューモデル
 * Created by keisuke on 2016/09/18.
 */

public class RepoListItemViewModel extends FragmentViewModel{

    private final StarUseCase starUseCase;
    private final UnstarUseCase unstarUseCase;

    public ObservableField<Repo> repo;
    public ObservableBoolean starred;

    @Inject
    public RepoListItemViewModel(BaseFragment fragment,
                                 StarUseCase starUseCase,
                                 UnstarUseCase unstarUseCase) {
        super(fragment);
        this.starUseCase = starUseCase;
        this.unstarUseCase = unstarUseCase;

        this.repo = new ObservableField<>();
        this.starred = new ObservableBoolean(false);
    }

    public void onImageClick(View view) {
        UserActivity.startActivity(getFragment().getContext(), repo.get().owner.login);
    }

    public void onStarClick(View view) {
        if (starred.get()) {
            unstarUseCase.run(repo.get().owner.login, repo.get().name)
                    .compose(getFragment().bindToLifecycle().forCompletable())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(() -> {
                        starred.set(false);
                        Toast.makeText(getFragment().getContext(), "Unstarred!", Toast.LENGTH_SHORT).show();
                    });
        } else {
            starUseCase.run(repo.get().owner.login, repo.get().name)
                    .compose(getFragment().bindToLifecycle().forCompletable())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(() -> {
                        starred.set(true);
                        Toast.makeText(getFragment().getContext(), "Starred!", Toast.LENGTH_SHORT).show();
                    });
        }
    }
}
