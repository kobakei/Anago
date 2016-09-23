package io.github.kobakei.anago.fragment;

import com.trello.rxlifecycle.components.support.RxFragment;

import io.github.kobakei.anago.AnagoApplication;
import io.github.kobakei.anago.di.FragmentComponent;
import io.github.kobakei.anago.di.FragmentModule;
import io.github.kobakei.anago.viewmodel.FragmentViewModel;

/**
 * Fragmentのベースクラス
 * Created by keisuke on 2016/09/19.
 */

public abstract class BaseFragment extends RxFragment{

    private FragmentViewModel viewModel;

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
    public void onResume() {
        super.onResume();
        if (viewModel == null) {
            throw new IllegalStateException("Before resuming activity, bindViewModel must be called.");
        }
        viewModel.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        viewModel.onPause();
    }
}
