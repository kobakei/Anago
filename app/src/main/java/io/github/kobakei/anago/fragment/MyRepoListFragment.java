package io.github.kobakei.anago.fragment;


import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import javax.inject.Inject;

import io.github.kobakei.anago.R;
import io.github.kobakei.anago.databinding.MyRepoListFragmentBinding;
import io.github.kobakei.anago.databinding.RepoListItemBinding;
import io.github.kobakei.anago.entity.Repo;
import io.github.kobakei.anago.viewmodel.MyRepoListViewModel;

/**
 * リポジトリ一覧画面
 */
public class MyRepoListFragment extends BaseFragment {

    @Inject
    MyRepoListViewModel viewModel;

    public MyRepoListFragment() {
        // Required empty public constructor
    }

    public static MyRepoListFragment newInstance() {
        MyRepoListFragment fragment = new MyRepoListFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        getInjector().inject(this);
        bindViewModel(viewModel);

        MyRepoListFragmentBinding binding = DataBindingUtil.inflate(inflater, R.layout.my_repo_list_fragment, container, false);
        binding.setViewModel(viewModel);

        // Set up listview
        RepoAdapter adapter = new RepoAdapter(getContext(), viewModel.repos);
        binding.listView.setAdapter(adapter);

        return binding.getRoot();
    }

    /**
     * リストビューのアダプター
     */
    class RepoAdapter extends ArrayAdapter<Pair<Repo, Boolean>> {
        public RepoAdapter(Context context, ObservableArrayList<Pair<Repo, Boolean>> objects) {
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
                binding.setViewModel(getInjector().repoListItemViewModel());
            } else {
                binding = DataBindingUtil.getBinding(convertView);
            }

            Repo repo = getItem(position).first;
            boolean starred = getItem(position).second;
            binding.getViewModel().repo.set(repo);
            binding.getViewModel().starred.set(starred);

            return binding.getRoot();
        }
    }
}
