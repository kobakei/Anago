package io.github.kobakei.anago.activity;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;

import javax.inject.Inject;

import io.github.kobakei.anago.R;
import io.github.kobakei.anago.databinding.RepoListActivityBinding;
import io.github.kobakei.anago.databinding.RepoListItemBinding;
import io.github.kobakei.anago.entity.Repo;
import io.github.kobakei.anago.viewmodel.RepoListItemViewModel;
import io.github.kobakei.anago.viewmodel.RepoListViewModel;

/**
 * リポジトリ一覧画面
 */
public class RepoListActivity extends BaseActivity {

    @Inject
    RepoListViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getInjector().inject(this);

        RepoListActivityBinding binding = DataBindingUtil.setContentView(this, R.layout.repo_list_activity);
        binding.setViewModel(viewModel);

        RepoAdapter adapter = new RepoAdapter(this, viewModel.repos);
        binding.listView.setAdapter(adapter);
    }

    /**
     * リストビューのアダプター
     */
    static class RepoAdapter extends ArrayAdapter<Repo> {
        public RepoAdapter(Context context, ObservableArrayList<Repo> objects) {
            super(context, 0, objects);
            objects.addOnListChangedCallback(new ObservableList.OnListChangedCallback<ObservableList<Repo>>() {
                @Override
                public void onChanged(ObservableList<Repo> repos) {
                    notifyDataSetChanged();
                }

                @Override
                public void onItemRangeChanged(ObservableList<Repo> repos, int i, int i1) {
                    notifyDataSetChanged();
                }

                @Override
                public void onItemRangeInserted(ObservableList<Repo> repos, int i, int i1) {
                    notifyDataSetChanged();
                }

                @Override
                public void onItemRangeMoved(ObservableList<Repo> repos, int i, int i1, int i2) {
                    notifyDataSetChanged();
                }

                @Override
                public void onItemRangeRemoved(ObservableList<Repo> repos, int i, int i1) {
                    notifyDataSetChanged();
                }
            });
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            RepoListItemBinding binding;
            if (convertView == null) {
                binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.repo_list_item, parent, false);
                binding.setViewModel(new RepoListItemViewModel());
            } else {
                binding = DataBindingUtil.getBinding(convertView);
            }

            Repo repo = getItem(position);
            binding.getViewModel().repo.set(repo);

            return binding.getRoot();
        }
    }
}
