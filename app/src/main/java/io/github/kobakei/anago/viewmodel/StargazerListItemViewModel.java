package io.github.kobakei.anago.viewmodel;

import android.databinding.ObservableField;
import android.view.View;

import javax.inject.Inject;

import io.github.kobakei.anago.activity.BaseActivity;
import io.github.kobakei.anago.activity.UserActivity;
import io.github.kobakei.anago.entity.User;

/**
 * スターした人アイテムのビューモデル
 * Created by keisuke on 2016/09/20.
 */

public class StargazerListItemViewModel extends ViewModel {

    public ObservableField<User> user;

    @Inject
    public StargazerListItemViewModel(BaseActivity activity) {
        super(activity);

        this.user = new ObservableField<>();
    }

    public void onImageClick(View view) {
        UserActivity.startActivity(getActivity(), user.get().login);
    }
}
