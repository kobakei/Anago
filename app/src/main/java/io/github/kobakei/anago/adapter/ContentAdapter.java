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
import io.github.kobakei.anago.databinding.ContentListItemBinding;
import io.github.kobakei.anago.di.FragmentComponent;
import io.github.kobakei.anago.entity.Content;
import io.github.kobakei.anago.entity.Issue;
import io.github.kobakei.anago.viewmodel.ContentListItemViewModel;

/**
 * イシューリストのアダプター
 * Created by keisuke on 2016/10/13.
 */

public class ContentAdapter extends ArrayAdapter<Content> {

    private final FragmentComponent injector;

    /**
     * コンストラクタ
     * @param context
     * @param injector
     * @param items
     */
    public ContentAdapter(Context context, FragmentComponent injector, ObservableArrayList<Content> items) {
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
        ContentListItemBinding binding;
        if (convertView == null) {
            binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.content_list_item, parent, false);
            binding.setViewModel(injector.contentListItemViewModel());
        } else {
            binding = DataBindingUtil.getBinding(convertView);
        }

        ContentListItemViewModel viewModel = binding.getViewModel();
        viewModel.content.set(getItem(position));

        return binding.getRoot();
    }
}
