package io.github.kobakei.anago.activity;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import io.github.kobakei.anago.R;
import io.github.kobakei.anago.databinding.StargazerListActivityBinding;
import io.github.kobakei.anago.databinding.StargazerListItemBinding;
import io.github.kobakei.anago.entity.User;
import io.github.kobakei.anago.viewmodel.StargazerListViewModel;

/**
 * スターした人一覧画面
 */
public class StargazerListActivity extends BaseActivity {

    private static final String KEY_USER = "user";
    private static final String KEY_REPO = "repo";

    @Inject
    StargazerListViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getInjector().inject(this);
        bindViewModel(viewModel);

        StargazerListActivityBinding binding = DataBindingUtil.setContentView(this, R.layout.stargazer_list_activity);
        binding.setViewModel(viewModel);

        String user = getIntent().getStringExtra(KEY_USER);
        String repo = getIntent().getStringExtra(KEY_REPO);
        viewModel.setParams(user, repo);

        // Set up recycler view
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setAdapter(new StargazerAdapter(viewModel.users));

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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

    public static void startActivity(Activity activity, String user, String repo) {
        Intent intent = new Intent(activity, StargazerListActivity.class);
        intent.putExtra(KEY_USER, user);
        intent.putExtra(KEY_REPO, repo);
        activity.startActivity(intent);
    }

    /**
     * ビューホルダー
     */
    class StargazerViewHolder extends RecyclerView.ViewHolder {

        private final StargazerListItemBinding binding;

        public StargazerViewHolder(StargazerListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public StargazerListItemBinding getBinding() {
            return binding;
        }
    }

    /**
     * アダプター
     */
    class StargazerAdapter extends RecyclerView.Adapter<StargazerViewHolder> {

        private ObservableArrayList<User> users;

        public StargazerAdapter(ObservableArrayList<User> users) {
            this.users = users;
            this.users.addOnListChangedCallback(new ObservableList.OnListChangedCallback<ObservableList<User>>() {
                @Override
                public void onChanged(ObservableList<User> users) {
                    notifyDataSetChanged();
                }

                @Override
                public void onItemRangeChanged(ObservableList<User> users, int i, int i1) {
                    notifyDataSetChanged();
                }

                @Override
                public void onItemRangeInserted(ObservableList<User> users, int i, int i1) {
                    notifyDataSetChanged();
                }

                @Override
                public void onItemRangeMoved(ObservableList<User> users, int i, int i1, int i2) {
                    notifyDataSetChanged();
                }

                @Override
                public void onItemRangeRemoved(ObservableList<User> users, int i, int i1) {
                    notifyDataSetChanged();
                }
            });
        }

        @Override
        public StargazerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getApplicationContext());
            StargazerListItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.stargazer_list_item, parent, false);
            binding.setViewModel(getInjector().stargazerListItemViewModel());
            return new StargazerViewHolder(binding);
        }

        @Override
        public void onBindViewHolder(StargazerViewHolder holder, int position) {
            User user = users.get(position);
            holder.getBinding().getViewModel().user.set(user);
        }

        @Override
        public int getItemCount() {
            return users.size();
        }
    }
}
