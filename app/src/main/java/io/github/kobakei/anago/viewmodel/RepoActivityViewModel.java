package io.github.kobakei.anago.viewmodel;

import android.databinding.ObservableField;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

import io.github.kobakei.anago.activity.BaseActivity;
import io.github.kobakei.anago.entity.Repo;
import io.github.kobakei.anago.viewmodel.base.ActivityViewModel;

/**
 * リポジトリ画面ビューモデル
 * Created by keisuke on 2016/10/07.
 */

public class RepoActivityViewModel extends ActivityViewModel
        implements Toolbar.OnMenuItemClickListener {

    EventBus eventBus;

    public ObservableField<String> user = new ObservableField<>();
    public ObservableField<String> repo = new ObservableField<>();

    @Inject
    public RepoActivityViewModel(BaseActivity activity, EventBus eventBus) {
        super(activity);
        this.eventBus = eventBus;
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

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        eventBus.post(new ShowAllIssuesEvent());
        return true;
    }

    public void onNavigationClick(View view) {
        getActivity().finish();
    }

    public static class ShowAllIssuesEvent {}
}
