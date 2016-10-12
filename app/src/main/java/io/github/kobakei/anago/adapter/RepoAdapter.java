package io.github.kobakei.anago.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.support.annotation.NonNull;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import io.github.kobakei.anago.R;
import io.github.kobakei.anago.databinding.RepoListItemBinding;
import io.github.kobakei.anago.di.FragmentComponent;
import io.github.kobakei.anago.entity.Repo;

/**
 * Created by keisuke on 2016/10/13.
 */

public class RepoAdapter extends ArrayAdapter<Pair<Repo, Boolean>> {

    private final FragmentComponent injector;

    public RepoAdapter(Context context, FragmentComponent injector, ObservableArrayList<Pair<Repo, Boolean>> objects) {
        super(context, 0, objects);

        this.injector = injector;

        objects.addOnListChangedCallback(new ObservableList.OnListChangedCallback<ObservableList<Repo>>() {
            @Override
            public void onChanged(ObservableList<Repo> repos) {
                notifyDataSetChanged();
            }

            @Override
            public void onItemRangeChanged(ObservableList<Repo> repos, int i, int i1) {
                notifyDataSetChanged();
            }

            @Override
            public void onItemRangeInserted(ObservableList<Repo> repos, int i, int i1) {
                notifyDataSetChanged();
            }

            @Override
            public void onItemRangeMoved(ObservableList<Repo> repos, int i, int i1, int i2) {
                notifyDataSetChanged();
            }

            @Override
            public void onItemRangeRemoved(ObservableList<Repo> repos, int i, int i1) {
                notifyDataSetChanged();
            }
        });
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        RepoListItemBinding binding;
        if (convertView == null) {
            binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.repo_list_item, parent, false);
            binding.setViewModel(injector.repoListItemViewModel());
        } else {
            binding = DataBindingUtil.getBinding(convertView);
        }

        Repo repo = getItem(position).first;
        boolean starred = getItem(position).second;
        binding.getViewModel().repo.set(repo);
        binding.getViewModel().starred.set(starred);

        return binding.getRoot();
    }
}
