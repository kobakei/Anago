package io.github.kobakei.anago.fragment;


import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import javax.inject.Inject;

import io.github.kobakei.anago.R;
import io.github.kobakei.anago.databinding.IssueListFragmentBinding;
import io.github.kobakei.anago.databinding.IssueListItemBinding;
import io.github.kobakei.anago.entity.Issue;
import io.github.kobakei.anago.viewmodel.IssueListItemViewModel;
import io.github.kobakei.anago.viewmodel.IssueListViewModel;

/**
 * イシュー画面
 */
public class IssueListFragment extends BaseFragment {

    private static final String ARG_USER = "user";
    private static final String ARG_REPO = "repo";

    private String user;
    private String repo;

    @Inject
    IssueListViewModel viewModel;

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
            user = getArguments().getString(ARG_USER);
            repo = getArguments().getString(ARG_REPO);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        IssueListFragmentBinding binding = DataBindingUtil.inflate(inflater, R.layout.issue_list_fragment, container, false);

        binding.listView.setAdapter(new IssueAdapter(getContext(), viewModel.issues));

        return binding.getRoot();
    }

    /**
     *
     */
    class IssueAdapter extends ArrayAdapter<Issue> {
        public IssueAdapter(Context context, ObservableArrayList<Issue> items) {
            super(context, 0, items);
            items.addOnListChangedCallback(new ObservableList.OnListChangedCallback<ObservableList<Issue>>() {
                @Override
                public void onChanged(ObservableList<Issue> issues) {
                    notifyDataSetChanged();
                }

                @Override
                public void onItemRangeChanged(ObservableList<Issue> issues, int i, int i1) {
                    notifyDataSetChanged();
                }

                @Override
                public void onItemRangeInserted(ObservableList<Issue> issues, int i, int i1) {
                    notifyDataSetChanged();
                }

                @Override
                public void onItemRangeMoved(ObservableList<Issue> issues, int i, int i1, int i2) {
                    notifyDataSetChanged();
                }

                @Override
                public void onItemRangeRemoved(ObservableList<Issue> issues, int i, int i1) {
                    notifyDataSetChanged();
                }
            });
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            IssueListItemBinding binding;
            if (convertView == null) {
                binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.issue_list_item, parent, false);
                binding.setViewModel(getInjector().issueListItemViewModel());
            } else {
                binding = DataBindingUtil.getBinding(convertView);
            }

            IssueListItemViewModel viewModel = binding.getViewModel();
            viewModel.issue.set(getItem(position));

            return binding.getRoot();
        }
    }
}
