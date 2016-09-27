package io.github.kobakei.anago.viewmodel;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.support.v4.util.Pair;
import android.view.View;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

import io.github.kobakei.anago.activity.BaseActivity;
import io.github.kobakei.anago.activity.StargazerListActivity;
import io.github.kobakei.anago.activity.UserActivity;
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
    public ObservableBoolean isConnecting;
    public ObservableBoolean starred;

    private String paramUser;
    private String paramRepo;

    @Inject
    public RepoViewModel(BaseActivity activity, GetRepoUseCase getRepoUseCase,
                         CheckStarUseCase checkStarUseCase, StarUseCase starUseCase,
                         UnstarUseCase unstarUseCase) {
        super(activity);
        this.getRepoUseCase = getRepoUseCase;
        this.checkStarUseCase = checkStarUseCase;
        this.starUseCase = starUseCase;
        this.unstarUseCase = unstarUseCase;

        this.repo = new ObservableField<>();
        this.isConnecting = new ObservableBoolean(true);
        this.starred = new ObservableBoolean(false);
    }

    public void setParams(String user, String repo) {
        this.paramUser = user;
        this.paramRepo = repo;
    }

    @Override
    public void onResume() {
        super.onResume();

        refreshRepo();
    }

    public void onStarClick(View view) {
        starUseCase.run(repo.get().owner.login, repo.get().name)
                .compose(getActivity().bindToLifecycle().forCompletable())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> {
                    this.starred.set(true);
                    refreshRepo();
                    Toast.makeText(getActivity(), "Starred!", Toast.LENGTH_SHORT).show();
                }, throwable -> {
                    Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
                });
    }

    public void onUnstarClick(View view) {
        unstarUseCase.run(repo.get().owner.login, repo.get().name)
                .compose(getActivity().bindToLifecycle().forCompletable())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> {
                    this.starred.set(false);
                    refreshRepo();
                    Toast.makeText(getActivity(), "Unstarred!", Toast.LENGTH_SHORT).show();
                }, throwable -> {
                    Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
                });
    }

    public void onImageClick(View view) {
        UserActivity.startActivity(getActivity(), repo.get().owner.login);
    }

    public void onStargazerClick(View view) {
        StargazerListActivity.startActivity(getActivity(), repo.get().owner.login, repo.get().name);
    }

    private void refreshRepo() {
        getRepoUseCase.run(paramUser, paramRepo)
                .flatMapObservable(repo1 -> Observable.combineLatest(
                        Observable.just(repo1),
                        checkStarUseCase.run(repo1.owner.login, repo1.name).toObservable(),
                        Pair::create
                ))
                .toSingle()
                .compose(getActivity().bindToLifecycle().forSingle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(pair -> {
                    this.isConnecting.set(false);
                    this.repo.set(pair.first);
                    this.starred.set(pair.second);

                    EventBus.getDefault().post(new RefreshRepoEvent(pair.first));

                }, Throwable::printStackTrace);
    }

    public static class RefreshRepoEvent {
        public final Repo repo;
        public RefreshRepoEvent(Repo repo) {
            this.repo = repo;
        }
    }
}
