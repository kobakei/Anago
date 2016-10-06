package io.github.kobakei.anago.viewmodel;

import javax.inject.Inject;

import io.github.kobakei.anago.activity.BaseActivity;

/**
 * リポジトリ画面ビューモデル
 * Created by keisuke on 2016/10/07.
 */

public class RepoViewModel extends ViewModel {

    @Inject
    public RepoViewModel(BaseActivity activity) {
        super(activity);
    }
}
