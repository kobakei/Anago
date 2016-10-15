package io.github.kobakei.anago.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.MenuItem;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import javax.inject.Inject;

import io.github.kobakei.anago.R;
import io.github.kobakei.anago.databinding.UserActivityBinding;
import io.github.kobakei.anago.viewmodel.UserActivityViewModel;

/**
 * ユーザー情報画面
 */
public class UserActivity extends BaseActivity {

    private static final String KEY_NAME = "name";

    @Inject
    UserActivityViewModel viewModel;

    @Inject
    EventBus eventBus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getInjector().inject(this);
        bindViewModel(viewModel);

        UserActivityBinding binding = DataBindingUtil.setContentView(this, R.layout.user_activity);
        binding.setViewModel(viewModel);

        String name = getIntent().getStringExtra(KEY_NAME);
        viewModel.setParams(name);
    }

    @Override
    protected void onResume() {
        super.onResume();
        eventBus.register(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        eventBus.unregister(this);
    }

    public static void startActivity(Context context, String name) {
        Intent intent = new Intent(context, UserActivity.class);
        intent.putExtra(KEY_NAME, name);
        context.startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return false;
    }

    @Subscribe
    public void onUserRefreshed(UserActivityViewModel.RefreshUserEvent event) {
        getSupportActionBar().setTitle(event.user.login);
    }
}
