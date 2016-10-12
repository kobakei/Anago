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
import io.github.kobakei.anago.databinding.IssueListItemBinding;
import io.github.kobakei.anago.di.FragmentComponent;
import io.github.kobakei.anago.entity.Issue;
import io.github.kobakei.anago.viewmodel.IssueListItemViewModel;

/**
 * イシューリストのアダプター
 * Created by keisuke on 2016/10/13.
 */

public class IssueAdapter extends ArrayAdapter<Issue> {

    private final FragmentComponent injector;

    /**
     * コンストラクタ
     * @param context
     * @param injector
     * @param items
     */
    public IssueAdapter(Context context, FragmentComponent injector, ObservableArrayList<Issue> items) {
        super(context, 0, items);

        this.injector = injector;

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
            binding.setViewModel(injector.issueListItemViewModel());
        } else {
            binding = DataBindingUtil.getBinding(convertView);
        }

        IssueListItemViewModel viewModel = binding.getViewModel();
        viewModel.issue.set(getItem(position));

        return binding.getRoot();
    }
}
