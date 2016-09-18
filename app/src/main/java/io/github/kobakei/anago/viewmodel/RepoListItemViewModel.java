package io.github.kobakei.anago.viewmodel;

import android.content.Context;
import android.databinding.ObservableField;
import android.view.View;
import android.widget.Toast;

import javax.inject.Inject;

import io.github.kobakei.anago.entity.Repo;
import rx.Observable;

/**
 * リポジトリ一覧アイテムのビューモデル
 * Created by keisuke on 2016/09/18.
 */

public class RepoListItemViewModel extends ListItemViewModel {

    public ObservableField<Repo> repo;

    @Inject
    public RepoListItemViewModel(Context context) {
        super(context);
        this.repo = new ObservableField<>();
    }

    public void onImageClick(View view) {
        Toast.makeText(getContext(), "Image click", Toast.LENGTH_SHORT).show();
    }
}
