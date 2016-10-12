package io.github.kobakei.anago.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import io.github.kobakei.anago.R;
import io.github.kobakei.anago.databinding.PullRequestListItemBinding;
import io.github.kobakei.anago.di.FragmentComponent;
import io.github.kobakei.anago.entity.Issue;
import io.github.kobakei.anago.entity.PullRequest;
import io.github.kobakei.anago.viewmodel.PullRequestListItemViewModel;

/**
 * プルリクリストのアダプター
 * Created by keisuke on 2016/10/13.
 */

public class PullRequestAdapter extends ArrayAdapter<PullRequest> {

    private final FragmentComponent injector;

    /**
     * コンストラクタ
     * @param context
     * @param injector
     * @param items
     */
    public PullRequestAdapter(Context context, FragmentComponent injector, ObservableArrayList<PullRequest> items) {
        super(context, 0, items);

        this.injector = injector;

        items.addOnListChangedCallback(new ObservableList.OnListChangedCallback<ObservableList<PullRequest>>() {
            @Override
            public void onChanged(ObservableList<PullRequest> pullRequests) {
                notifyDataSetChanged();
            }

            @Override
            public void onItemRangeChanged(ObservableList<PullRequest> pullRequests, int i, int i1) {
                notifyDataSetChanged();
            }

            @Override
            public void onItemRangeInserted(ObservableList<PullRequest> pullRequests, int i, int i1) {
                notifyDataSetChanged();
            }

            @Override
            public void onItemRangeMoved(ObservableList<PullRequest> pullRequests, int i, int i1, int i2) {
                notifyDataSetChanged();
            }

            @Override
            public void onItemRangeRemoved(ObservableList<PullRequest> pullRequests, int i, int i1) {
                notifyDataSetChanged();
            }
        });
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        PullRequestListItemBinding binding;
        if (convertView == null) {
            binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.pull_request_list_item, parent, false);
            binding.setViewModel(injector.pullRequestListItemViewModel());
        } else {
            binding = DataBindingUtil.getBinding(convertView);
        }

        PullRequestListItemViewModel viewModel = binding.getViewModel();
        viewModel.pullRequest.set(getItem(position));

        return binding.getRoot();
    }
}
