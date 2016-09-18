package io.github.kobakei.anago.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import javax.inject.Inject;

import io.github.kobakei.anago.R;
import io.github.kobakei.anago.databinding.HomeActivityBinding;
import io.github.kobakei.anago.fragment.RepoListFragment;
import io.github.kobakei.anago.viewmodel.RepoListViewModel;

/**
 * リポジトリ一覧画面
 */
public class HomeActivity extends BaseActivity {

    @Inject
    RepoListViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getInjector().inject(this);

        HomeActivityBinding binding = DataBindingUtil.setContentView(this, R.layout.home_activity);
        binding.setViewModel(viewModel);

        // Set up viewpager
        HomeFragmentPagerAdapter adapter = new HomeFragmentPagerAdapter(getSupportFragmentManager());
        binding.viewPager.setAdapter(adapter);
        binding.tabLayout.setupWithViewPager(binding.viewPager);
    }

    /**
     * ViewPager用のアダプター
     */
    static class HomeFragmentPagerAdapter extends FragmentPagerAdapter {

        public HomeFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return RepoListFragment.newInstance();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "Title " + position;
        }

        @Override
        public int getCount() {
            return 2;
        }
    }
}
