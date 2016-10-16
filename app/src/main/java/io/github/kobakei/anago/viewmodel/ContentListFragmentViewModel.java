package io.github.kobakei.anago.viewmodel;

import android.databinding.ObservableArrayList;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import javax.inject.Inject;

import io.github.kobakei.anago.entity.Content;
import io.github.kobakei.anago.entity.Issue;
import io.github.kobakei.anago.fragment.BaseFragment;
import io.github.kobakei.anago.usecase.GetFilesUseCase;
import io.github.kobakei.anago.usecase.GetIssuesUseCase;
import io.github.kobakei.anago.viewmodel.base.FragmentViewModel;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by keisuke on 2016/10/09.
 */

public class ContentListFragmentViewModel extends FragmentViewModel {

    private final GetFilesUseCase getFilesUseCase;

    public ObservableArrayList<Content> contents;

    private String user;
    private String repo;
    private String path = "";

    @Inject
    public ContentListFragmentViewModel(BaseFragment fragment, GetFilesUseCase getFilesUseCase) {
        super(fragment);
        this.getFilesUseCase = getFilesUseCase;

        this.contents = new ObservableArrayList<>();
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onResume() {
        refresh();
    }

    @Override
    public void onPause() {
    }

    @Override
    public void onStop() {
    }

    public void setRepo(String user, String repo) {
        this.user = user;
        this.repo = repo;
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        Content content = this.contents.get(position);
        if (content.type.equals("file")) {
            Toast.makeText(getContext(), "File", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(), "Dir", Toast.LENGTH_SHORT).show();
            this.path = content.path;
            refresh();
        }
    }

    private void refresh() {
        getFilesUseCase.run(user, repo, path)
                .compose(bindToLifecycle().forSingle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(contents1 -> {
                    this.contents.clear();
                    this.contents.addAll(contents1);
                }, throwable -> {
                    Timber.e(throwable);
                });
    }
}
