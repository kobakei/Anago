package io.github.kobakei.anago.activity;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import javax.inject.Inject;

import io.github.kobakei.anago.R;
import io.github.kobakei.anago.databinding.RepoActivityBinding;
import io.github.kobakei.anago.viewmodel.RepoViewModel;

/**
 * リポジトリ詳細画面
 */
public class RepoActivity extends BaseActivity {

    @Inject
    RepoViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getInjector().inject(this);

        RepoActivityBinding binding = DataBindingUtil.setContentView(this, R.layout.repo_activity);
        binding.setViewModel(viewModel);
    }

    @Override
    protected void onResume() {
        super.onResume();
        viewModel.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        viewModel.onPause();
    }

    public static void startActivity(Activity activity, long id) {
        Intent intent = new Intent(activity, RepoActivity.class);
        intent.putExtra("id", id);
        activity.startActivity(intent);
    }
}
