package io.github.kobakei.anago.viewmodel;

import android.app.Activity;
import android.databinding.ObservableField;

import javax.inject.Inject;

import io.github.kobakei.anago.entity.Repo;

/**
 * リポジトリ詳細画面のビューモデル
 * Created by keisuke on 2016/09/18.
 */

public class RepoViewModel extends ActivityViewModel {

    public ObservableField<Repo> repo;

    @Inject
    public RepoViewModel(Activity activity) {
        super(activity);

        this.repo = new ObservableField<>();
    }
}
