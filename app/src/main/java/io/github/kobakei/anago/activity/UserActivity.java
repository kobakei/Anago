package io.github.kobakei.anago.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;

import javax.inject.Inject;

import io.github.kobakei.anago.R;
import io.github.kobakei.anago.databinding.UserActivityBinding;
import io.github.kobakei.anago.viewmodel.UserViewModel;

/**
 * ユーザー情報画面
 */
public class UserActivity extends BaseActivity {

    @Inject
    UserViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getInjector().inject(this);

        UserActivityBinding binding = DataBindingUtil.setContentView(this, R.layout.user_activity);
        binding.setViewModel(viewModel);
    }

    public static void startActivity(Context context, String name) {
        Intent intent = new Intent(context, UserActivity.class);
        intent.putExtra("name", name);
        context.startActivity(intent);
    }
}
