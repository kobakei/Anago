package io.github.kobakei.anago.viewmodel;

import android.app.Activity;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.view.View;

import javax.inject.Inject;

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
    }

    public void onButtonClick(View view) {

    }
}
