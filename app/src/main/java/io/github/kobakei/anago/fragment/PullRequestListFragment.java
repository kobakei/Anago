package io.github.kobakei.anago.fragment;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import io.github.kobakei.anago.R;
import io.github.kobakei.anago.adapter.PullRequestAdapter;
import io.github.kobakei.anago.databinding.PullRequestListFragmentBinding;
import io.github.kobakei.anago.viewmodel.PullRequestListFragmentViewModel;

/**
 * プルリク画面
 */
public class PullRequestListFragment extends BaseFragment {

    private static final String ARG_USER = "user";
    private static final String ARG_REPO = "repo";

    @Inject
    PullRequestListFragmentViewModel viewModel;

    public PullRequestListFragment() {
        // Required empty public constructor
    }

    public static PullRequestListFragment newInstance(String param1, String param2) {
        PullRequestListFragment fragment = new PullRequestListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_USER, param1);
        args.putString(ARG_REPO, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getInjector().inject(this);
        bindViewModel(viewModel);

        if (getArguments() != null) {
            String user = getArguments().getString(ARG_USER);
            String repo = getArguments().getString(ARG_REPO);
            viewModel.setRepo(user, repo);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        PullRequestListFragmentBinding binding = DataBindingUtil.inflate(inflater, R.layout.pull_request_list_fragment, container, false);

        binding.listView.setAdapter(new PullRequestAdapter(getContext(), getInjector(), viewModel.pullRequests));

        return binding.getRoot();
    }
}
