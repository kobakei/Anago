package io.github.kobakei.anago.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import io.github.kobakei.anago.R;
import io.github.kobakei.anago.databinding.RepoInfoFragmentBinding;
import io.github.kobakei.anago.viewmodel.RepoInfoFragmentViewModel;

/**
 * リポジトリ詳細
 * Created by keisuke on 2016/10/07.
 */

public class RepoInfoFragment extends BaseFragment {

    @Inject
    RepoInfoFragmentViewModel viewModel;

    private String user;
    private String repo;

    public static RepoInfoFragment newInstance(final String user, final String repo) {
        RepoInfoFragment fragment = new RepoInfoFragment();
        Bundle args = new Bundle();
        args.putString("user", user);
        args.putString("repo", repo);
        fragment.setArguments(args);
        return fragment;
    }

    public RepoInfoFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        user = args.getString("user");
        repo = args.getString("repo");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getInjector().inject(this);
        bindViewModel(viewModel);

        RepoInfoFragmentBinding binding = DataBindingUtil.inflate(inflater, R.layout.repo_info_fragment, container, false);
        binding.setViewModel(viewModel);
        viewModel.setParams(user, repo);
        return binding.getRoot();
    }
}
