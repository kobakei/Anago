package io.github.kobakei.anago.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.MenuItem;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import javax.inject.Inject;

import io.github.kobakei.anago.R;
import io.github.kobakei.anago.databinding.RepoActivityBinding;
import io.github.kobakei.anago.fragment.RepoInfoFragment;
import io.github.kobakei.anago.viewmodel.RepoInfoViewModel;
import io.github.kobakei.anago.viewmodel.RepoViewModel;

/**
 * リポジトリ詳細画面
 */
public class RepoActivity extends BaseActivity {

    private static final String KEY_USER = "user";
    private static final String KEY_REPO = "repo";

    @Inject
    RepoViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getInjector().inject(this);
        bindViewModel(viewModel);

        RepoActivityBinding binding = DataBindingUtil.setContentView(this, R.layout.repo_activity);
        binding.setViewModel(viewModel);

        // 変数
        String user = getIntent().getStringExtra(KEY_USER);
        String repo = getIntent().getStringExtra(KEY_REPO);

        // タブ＋ページャー
        binding.tabLayout.setupWithViewPager(binding.viewPager);
        binding.viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                if (position == 0) {
                    return RepoInfoFragment.newInstance(user, repo);
                } else {
                    return RepoInfoFragment.newInstance(user, repo);
                }
            }

            @Override
            public CharSequence getPageTitle(int position) {
                if (position == 0) {
                    return "About";
                } else {
                    return "Code";
                }
            }

            @Override
            public int getCount() {
                return 2;
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
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
    public void onRepoRefreshed(RepoInfoViewModel.RefreshRepoEvent event) {
        getSupportActionBar().setTitle(event.repo.name);
    }

    /**
     * このActivityに遷移する
     * @param context
     * @param user
     * @param repo
     */
    public static void startActivity(Context context, String user, String repo) {
        Intent intent = new Intent(context, RepoActivity.class);
        intent.putExtra(KEY_USER, user);
        intent.putExtra(KEY_REPO, repo);
        context.startActivity(intent);
    }
}
