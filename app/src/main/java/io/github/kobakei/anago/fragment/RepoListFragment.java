package io.github.kobakei.anago.fragment;


import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import javax.inject.Inject;

import io.github.kobakei.anago.R;
import io.github.kobakei.anago.databinding.RepoListFragmentBinding;
import io.github.kobakei.anago.databinding.RepoListItemBinding;
import io.github.kobakei.anago.entity.Repo;
import io.github.kobakei.anago.viewmodel.RepoListItemViewModel;
import io.github.kobakei.anago.viewmodel.RepoListViewModel;

/**
 * リポジトリ一覧画面
 */
public class RepoListFragment extends BaseFragment {

    @Inject
    RepoListViewModel viewModel;

    public RepoListFragment() {
        // Required empty public constructor
    }

    public static RepoListFragment newInstance() {
        RepoListFragment fragment = new RepoListFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        getInjector().inject(this);

        RepoListFragmentBinding binding = DataBindingUtil.inflate(inflater, R.layout.repo_list_fragment, container, false);
        binding.setViewModel(viewModel);

        // Set up listview
        RepoAdapter adapter = new RepoAdapter(getContext(), viewModel.repos);
        binding.listView.setAdapter(adapter);

        return binding.getRoot();
    }

    /**
     * リストビューのアダプター
     */
    class RepoAdapter extends ArrayAdapter<Repo> {
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
                binding.setViewModel(getInjector().repoListItemViewModel());
            } else {
                binding = DataBindingUtil.getBinding(convertView);
            }

            Repo repo = getItem(position);
            binding.getViewModel().repo.set(repo);

            return binding.getRoot();
        }
    }
}
