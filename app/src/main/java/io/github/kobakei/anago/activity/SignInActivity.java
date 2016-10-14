package io.github.kobakei.anago.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;

import javax.inject.Inject;

import io.github.kobakei.anago.R;
import io.github.kobakei.anago.databinding.SignInActivityBinding;
import io.github.kobakei.anago.viewmodel.SignInActivityViewModel;

/**
 * サインイン画面
 */
public class SignInActivity extends BaseActivity {

    @Inject
    SignInActivityViewModel viewModel;

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

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, SignInActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
