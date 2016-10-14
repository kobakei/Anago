package io.github.kobakei.anago.fragment;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import io.github.kobakei.anago.R;
import io.github.kobakei.anago.adapter.RepoAdapter;
import io.github.kobakei.anago.databinding.StarredRepoListFragmentBinding;
import io.github.kobakei.anago.viewmodel.StarredRepoListFragmentViewModel;

/**
 * リポジトリ一覧画面
 */
public class StarredRepoListFragment extends BaseFragment {

    @Inject
    StarredRepoListFragmentViewModel viewModel;

    public StarredRepoListFragment() {
        // Required empty public constructor
    }

    public static StarredRepoListFragment newInstance() {
        StarredRepoListFragment fragment = new StarredRepoListFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        getInjector().inject(this);
        bindViewModel(viewModel);

        StarredRepoListFragmentBinding binding = DataBindingUtil.inflate(inflater, R.layout.starred_repo_list_fragment, container, false);
        binding.setViewModel(viewModel);

        // Set up listview
        RepoAdapter adapter = new RepoAdapter(getContext(), getInjector(), viewModel.repos);
        binding.listView.setAdapter(adapter);

        return binding.getRoot();
    }
}
