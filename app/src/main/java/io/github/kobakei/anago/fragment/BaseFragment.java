package io.github.kobakei.anago.fragment;

import com.trello.rxlifecycle.components.support.RxFragment;

import io.github.kobakei.anago.AnagoApplication;
import io.github.kobakei.anago.di.FragmentComponent;
import io.github.kobakei.anago.di.FragmentModule;
import io.github.kobakei.anago.viewmodel.base.ActivityViewModel;
import io.github.kobakei.anago.viewmodel.base.FragmentViewModel;

/**
 * Fragmentのベースクラス
 * Created by keisuke on 2016/09/19.
 */

public abstract class BaseFragment extends RxFragment{

    private FragmentViewModel viewModel;

    /**
     * DI
     * @return
     */
    protected FragmentComponent getInjector() {
        AnagoApplication application = (AnagoApplication) getContext().getApplicationContext();
        return application.getInjector().fragmentComponent(new FragmentModule(this));
    }

    /**
     * ビューモデルをこのフラグメントにバインドする
     * フラグメントのライフサイクルイベント発生時に、ビューモデルの対応するメソッドが呼ばれるようになります
     * @param viewModel
     */
    protected void bindViewModel(FragmentViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public void onStart() {
        super.onStart();
        checkViewModel();
        viewModel.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        checkViewModel();
        viewModel.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        checkViewModel();
        viewModel.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        checkViewModel();
        viewModel.onStop();
    }

    private void checkViewModel() {
        if (viewModel == null) {
            throw new IllegalStateException("Before resuming fragment, bindViewModel must be called.");
        }
    }
}
