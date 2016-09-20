package io.github.kobakei.anago.viewmodel;

import android.app.Activity;
import android.databinding.ObservableArrayList;

import javax.inject.Inject;

import io.github.kobakei.anago.entity.User;

/**
 * スターした人一覧画面のビューモデル
 * Created by keisuke on 2016/09/20.
 */

public class StargazerListViewModel extends ActivityViewModel {

    public ObservableArrayList<User> users;

    @Inject
    public StargazerListViewModel(Activity activity) {
        super(activity);

        this.users = new ObservableArrayList<>();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }
}
