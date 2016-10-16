package io.github.kobakei.anago.fragment;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import javax.inject.Inject;

import io.github.kobakei.anago.R;
import io.github.kobakei.anago.adapter.ContentAdapter;
import io.github.kobakei.anago.adapter.IssueAdapter;
import io.github.kobakei.anago.databinding.ContentListFragmentBinding;
import io.github.kobakei.anago.databinding.IssueListFragmentBinding;
import io.github.kobakei.anago.viewmodel.ContentListFragmentViewModel;
import io.github.kobakei.anago.viewmodel.IssueListFragmentViewModel;

/**
 * コード画面
 */
public class ContentListFragment extends BaseFragment {

    private static final String ARG_USER = "user";
    private static final String ARG_REPO = "repo";

    @Inject
    ContentListFragmentViewModel viewModel;

    public ContentListFragment() {
        // Required empty public constructor
    }

    public static ContentListFragment newInstance(String param1, String param2) {
        ContentListFragment fragment = new ContentListFragment();
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
        ContentListFragmentBinding binding = DataBindingUtil.inflate(inflater, R.layout.content_list_fragment, container, false);
        binding.setViewModel(viewModel);

        binding.listView.setAdapter(new ContentAdapter(getContext(), getInjector(), viewModel.contents));

        return binding.getRoot();
    }
}
