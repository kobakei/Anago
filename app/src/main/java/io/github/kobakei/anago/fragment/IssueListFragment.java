package io.github.kobakei.anago.fragment;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import io.github.kobakei.anago.R;
import io.github.kobakei.anago.adapter.IssueAdapter;
import io.github.kobakei.anago.databinding.IssueListFragmentBinding;
import io.github.kobakei.anago.viewmodel.IssueListFragmentViewModel;

/**
 * イシュー画面
 */
public class IssueListFragment extends BaseFragment {

    private static final String ARG_USER = "user";
    private static final String ARG_REPO = "repo";

    @Inject
    IssueListFragmentViewModel viewModel;

    public IssueListFragment() {
        // Required empty public constructor
    }

    public static IssueListFragment newInstance(String param1, String param2) {
        IssueListFragment fragment = new IssueListFragment();
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
        IssueListFragmentBinding binding = DataBindingUtil.inflate(inflater, R.layout.issue_list_fragment, container, false);

        binding.listView.setAdapter(new IssueAdapter(getContext(), getInjector(), viewModel.issues));

        return binding.getRoot();
    }
}
