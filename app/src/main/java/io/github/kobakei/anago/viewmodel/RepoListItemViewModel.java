package io.github.kobakei.anago.viewmodel;

import android.databinding.ObservableField;

import javax.inject.Inject;

import io.github.kobakei.anago.entity.Repo;
import rx.Observable;

/**
 * Created by keisuke on 2016/09/18.
 */

public class RepoListItemViewModel extends ListItemViewModel {

    public ObservableField<Repo> repo;

    @Inject
    public RepoListItemViewModel() {
        this.repo = new ObservableField<>();
    }
}
