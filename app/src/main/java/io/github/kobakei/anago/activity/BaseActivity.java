package io.github.kobakei.anago.activity;

import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import io.github.kobakei.anago.AnagoApplication;
import io.github.kobakei.anago.di.ActivityComponent;
import io.github.kobakei.anago.di.ActivityModule;
import io.github.kobakei.anago.viewmodel.base.ActivityViewModel;

/**
 * Activityのベースクラス
 * Created by keisuke on 2016/09/18.
 */

public class BaseActivity extends RxAppCompatActivity {

    private ActivityViewModel viewModel;

    protected ActivityComponent getInjector() {
        AnagoApplication application = (AnagoApplication) getApplication();
        return application.getInjector().activityComponent(new ActivityModule(this));
    }

    /**
     * ビューモデルをこのアクティビティにバインドする
     * アクティビティのライフサイクルイベント発生時に、ビューモデルの対応するメソッドが呼ばれるようになります
     * @param viewModel
     */
    protected void bindViewModel(ActivityViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    protected void onStart() {
        super.onStart();
        checkViewModel();
        viewModel.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        checkViewModel();
        viewModel.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        checkViewModel();
        viewModel.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        checkViewModel();
        viewModel.onStop();
    }

    private void checkViewModel() {
        if (viewModel == null) {
            throw new IllegalStateException("Before resuming activity, bindViewModel must be called.");
        }
    }
}
