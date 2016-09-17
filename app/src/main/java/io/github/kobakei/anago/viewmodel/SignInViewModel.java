package io.github.kobakei.anago.viewmodel;

import android.app.Activity;
import android.content.Intent;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.text.TextUtils;
import android.view.View;

import javax.inject.Inject;

import io.github.kobakei.anago.activity.RepoListActivity;
import timber.log.Timber;

/**
 * サインイン画面ビューモデル
 * Created by keisuke on 2016/09/18.
 */

public class SignInViewModel extends ActivityViewModel {

    // この画面の状態を表す変数
    public ObservableField<String> name;
    public ObservableField<String> password;
    public ObservableBoolean buttonEnabled;

    @Inject
    public SignInViewModel(Activity activity) {
        super(activity);
        name = new ObservableField<>();
        password = new ObservableField<>();
        buttonEnabled = new ObservableBoolean(false);
        Timber.v("Create");
    }

    public void onTextChanged(CharSequence s, int start, int before, int count) {
        Timber.v("text changed");
        buttonEnabled.set(!TextUtils.isEmpty(name.get()) && !TextUtils.isEmpty(password.get()));
    }

    public void onButtonClick(View view) {
        Timber.v("Button clicked. name=" + name.get());
        Intent intent = new Intent(getActivity(), RepoListActivity.class);
        getActivity().startActivity(intent);
        getActivity().finish();
    }
}
