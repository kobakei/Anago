package io.github.kobakei.anago.view;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import javax.inject.Inject;

import io.github.kobakei.anago.AnagoApplication;
import io.github.kobakei.anago.R;
import io.github.kobakei.anago.databinding.DebugViewBinding;
import io.github.kobakei.anago.di.ViewModule;
import io.github.kobakei.anago.viewmodel.DebugViewModel;

/**
 * カスタムビューのサンプル
 * Created by keisuke on 2016/10/08.
 */

public class DebugView extends FrameLayout {

    @Inject
    DebugViewModel viewModel;

    public DebugView(Context context) {
        super(context);
        init(context);
    }

    public DebugView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public DebugView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        AnagoApplication application = (AnagoApplication) context.getApplicationContext();
        application.getInjector().viewComponent(new ViewModule(this)).inject(this);

        LayoutInflater inflater = LayoutInflater.from(context);
        DebugViewBinding binding = DataBindingUtil.inflate(inflater, R.layout.debug_view, this, false);
        binding.setViewModel(viewModel);
        addView(binding.getRoot());
    }
}
