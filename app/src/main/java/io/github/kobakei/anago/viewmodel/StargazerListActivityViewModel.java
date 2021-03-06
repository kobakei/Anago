package io.github.kobakei.anago.viewmodel;

import android.databinding.ObservableArrayList;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import javax.inject.Inject;

import io.github.kobakei.anago.activity.BaseActivity;
import io.github.kobakei.anago.entity.User;
import io.github.kobakei.anago.usecase.GetStargazersUseCase;
import io.github.kobakei.anago.viewmodel.base.ActivityViewModel;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;

/**
 * スターした人一覧画面のビューモデル
 * Created by keisuke on 2016/09/20.
 */

public class StargazerListActivityViewModel extends ActivityViewModel {

    private final GetStargazersUseCase getStargazersUseCase;

    public ObservableArrayList<User> users;

    private String paramUser;
    private String paramRepo;

    private int page = 0;
    private boolean loadingMore = false;

    @Inject
    public StargazerListActivityViewModel(BaseActivity activity, GetStargazersUseCase getStargazersUseCase) {
        super(activity);
        this.getStargazersUseCase = getStargazersUseCase;

        this.users = new ObservableArrayList<>();
    }

    public void setParams(String user, String repo) {
        this.paramUser = user;
        this.paramRepo = repo;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onResume() {
        load();
    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {

    }

    public void onNavigationClick(View view) {
        getActivity().finish();
    }

    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        if (dy > 0) {
            LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
            int visible = layoutManager.getChildCount();
            int total = layoutManager.getItemCount();
            int first = layoutManager.findFirstVisibleItemPosition();
            if (visible + first >= total) {
                Timber.v("Scroll End");
                if (!loadingMore) {
                    Timber.v("Start loading more");
                    loadingMore = true;
                    load();
                }
            }
        }
    }

    private void load() {
        getStargazersUseCase.run(paramUser, paramRepo, page + 1, 20)
                .compose(bindToLifecycle().forSingle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(users1 -> {
                    this.users.addAll(users1);
                    this.page = page + 1;
                    this.loadingMore = false;
                }, Throwable::printStackTrace);
    }
}
