package io.github.kobakei.anago.activity;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import javax.inject.Inject;

import io.github.kobakei.anago.R;
import io.github.kobakei.anago.databinding.HomeActivityBinding;
import io.github.kobakei.anago.fragment.MyRepoListFragment;
import io.github.kobakei.anago.fragment.StarredRepoListFragment;
import io.github.kobakei.anago.viewmodel.HomeViewModel;

/**
 * リポジトリ一覧画面
 */
public class HomeActivity extends BaseActivity {

    @Inject
    HomeViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getInjector().inject(this);
        bindViewModel(viewModel);

        HomeActivityBinding binding = DataBindingUtil.setContentView(this, R.layout.home_activity);
        binding.setViewModel(viewModel);

        // Set up viewpager
        HomeFragmentPagerAdapter adapter = new HomeFragmentPagerAdapter(this, getSupportFragmentManager());
        binding.viewPager.setAdapter(adapter);
        binding.tabLayout.setupWithViewPager(binding.viewPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_home:
                viewModel.onSignOutClick();
                return true;
        }
        return false;
    }

    /**
     * ViewPager用のアダプター
     */
    static class HomeFragmentPagerAdapter extends FragmentPagerAdapter {

        private Activity activity;

        public HomeFragmentPagerAdapter(Activity activity, FragmentManager fm) {
            super(fm);
            this.activity = activity;
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0) {
                return MyRepoListFragment.newInstance();
            } else {
                return StarredRepoListFragment.newInstance();
            }
        }

        @Override
        public CharSequence getPageTitle(int position) {
            if (position == 0) {
                return activity.getString(R.string.home_tab_my);
            } else {
                return activity.getString(R.string.home_tab_starred);
            }
        }

        @Override
        public int getCount() {
            return 2;
        }
    }
}
