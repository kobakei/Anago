package io.github.kobakei.anago.viewmodel;

import android.databinding.ObservableField;

import javax.inject.Inject;

import io.github.kobakei.anago.entity.Issue;
import io.github.kobakei.anago.fragment.BaseFragment;
import io.github.kobakei.anago.viewmodel.base.FragmentViewModel;

/**
 * Created by keisuke on 2016/10/09.
 */

public class IssueListItemViewModel extends FragmentViewModel {

    public ObservableField<Issue> issue;

    @Inject
    public IssueListItemViewModel(BaseFragment fragment) {
        super(fragment);
        issue = new ObservableField<>();
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
