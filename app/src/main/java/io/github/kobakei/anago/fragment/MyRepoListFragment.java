package io.github.kobakei.anago.fragment;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import io.github.kobakei.anago.R;
import io.github.kobakei.anago.adapter.RepoAdapter;
import io.github.kobakei.anago.databinding.MyRepoListFragmentBinding;
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
        RepoAdapter adapter = new RepoAdapter(getContext(), getInjector(), viewModel.repos);
        binding.listView.setAdapter(adapter);

        return binding.getRoot();
    }
}
