package io.github.kobakei.anago.viewmodel;

import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import javax.inject.Inject;

import io.github.kobakei.anago.activity.BaseActivity;
import io.github.kobakei.anago.activity.SignInActivity;
import io.github.kobakei.anago.usecase.SignOutUseCase;
import io.github.kobakei.anago.viewmodel.base.ActivityViewModel;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * ホーム画面のビューモデル
 * Created by keisuke on 2016/09/19.
 */

public class HomeActivityViewModel extends ActivityViewModel implements Toolbar.OnMenuItemClickListener {

    private final SignOutUseCase signOutUseCase;

    @Inject
    public HomeActivityViewModel(BaseActivity activity, SignOutUseCase signOutUseCase) {
        super(activity);
        this.signOutUseCase = signOutUseCase;
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        signOutUseCase.run()
                .compose(bindToLifecycle().forCompletable())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> SignInActivity.startActivity(getContext()), Throwable::printStackTrace);
        return true;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {

    }
}
