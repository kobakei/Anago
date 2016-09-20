package io.github.kobakei.anago.viewmodel;

import android.content.Context;
import android.databinding.ObservableField;
import android.view.View;

import javax.inject.Inject;

import io.github.kobakei.anago.entity.User;

/**
 * Created by keisuke on 2016/09/20.
 */

public class StargazerListItemViewModel extends ListItemViewModel {

    public ObservableField<User> user;

    @Inject
    public StargazerListItemViewModel(Context context) {
        super(context);

        this.user = new ObservableField<>();
    }

    public void onImageClick(View view) {

    }
}
