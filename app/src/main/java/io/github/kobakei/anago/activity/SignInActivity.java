package io.github.kobakei.anago.activity;

import android.app.Activity;
import android.content.Intent;
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
        bindViewModel(viewModel);

        SignInActivityBinding binding = DataBindingUtil.setContentView(this, R.layout.sign_in_activity);
        binding.setViewModel(viewModel);

        // ランチャー名に影響しないように、ここでタイトルをセット
        getSupportActionBar().setTitle(R.string.sign_in_title);
    }

    public static void startActivity(Activity activity) {
        Intent intent = new Intent(activity, SignInActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
    }
}
