package io.github.kobakei.anago.viewmodel;

import android.databinding.adapters.AbsListViewBindingAdapter;
import android.widget.Toast;

import javax.inject.Inject;

import io.github.kobakei.anago.activity.BaseActivity;
import io.github.kobakei.anago.viewmodel.base.ActivityViewModel;

/**
 * リポジトリ画面ビューモデル
 * Created by keisuke on 2016/10/07.
 */

public class RepoActivityViewModel extends ActivityViewModel {

    @Inject
    public RepoActivityViewModel(BaseActivity activity) {
        super(activity);
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

    public void onPageSelected(int position) {
        Toast.makeText(getContext(), "Position " + position, Toast.LENGTH_SHORT).show();
    }
}
