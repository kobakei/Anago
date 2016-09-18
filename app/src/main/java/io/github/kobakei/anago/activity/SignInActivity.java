package io.github.kobakei.anago.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import javax.inject.Inject;

import io.github.kobakei.anago.R;
import io.github.kobakei.anago.databinding.SignInActivityBinding;
import io.github.kobakei.anago.viewmodel.SignInViewModel;

/**
 * サインイン画面
 */
public class SignInActivity extends BaseActivity {

    @Inject
    SignInViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getInjector().inject(this);

        SignInActivityBinding binding = DataBindingUtil.setContentView(this, R.layout.sign_in_activity);
        binding.setViewModel(viewModel);
    }
}
