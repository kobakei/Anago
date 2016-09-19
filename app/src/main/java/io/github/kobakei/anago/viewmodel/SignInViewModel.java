package io.github.kobakei.anago.viewmodel;

import android.app.Activity;
import android.content.Intent;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import javax.inject.Inject;

import io.github.kobakei.anago.activity.HomeActivity;
import io.github.kobakei.anago.usecase.CheckSessionUseCase;
import io.github.kobakei.anago.usecase.SignInUseCase;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;

/**
 * サインイン画面ビューモデル
 * Created by keisuke on 2016/09/18.
 */

public class SignInViewModel extends ActivityViewModel {

    // 使用するユースケース
    private final SignInUseCase signInUseCase;
    private final CheckSessionUseCase checkSessionUseCase;

    // この画面の状態を表す変数
    public ObservableField<String> name;
    public ObservableField<String> password;
    public ObservableBoolean buttonEnabled;

    @Inject
    public SignInViewModel(Activity activity, SignInUseCase signInUseCase,
                           CheckSessionUseCase checkSessionUseCase) {
        super(activity);

        this.signInUseCase = signInUseCase;
        this.checkSessionUseCase = checkSessionUseCase;

        name = new ObservableField<>();
        password = new ObservableField<>();
        buttonEnabled = new ObservableBoolean(false);

        Subscription subscription = checkSessionUseCase.run()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(authToken -> {
                    Timber.v("Check session: " + authToken.token);
                    Toast.makeText(getActivity(), "Already signed in", Toast.LENGTH_SHORT).show();
                    goToNext();
                }, Timber::e);
        getCompositeSubscription().add(subscription);
    }

    public void onTextChanged(CharSequence s, int start, int before, int count) {
        Timber.v("text changed");
        buttonEnabled.set(!TextUtils.isEmpty(name.get()) && !TextUtils.isEmpty(password.get()));
    }

    public void onButtonClick(View view) {
        Timber.v("Button clicked. name=" + name.get());

        Subscription subscription = signInUseCase.run(name.get(), password.get())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(authToken -> {
                    Toast.makeText(getActivity(), "Token: " + authToken.token, Toast.LENGTH_SHORT).show();
                    goToNext();
                }, throwable -> Timber.e(throwable));
        getCompositeSubscription().add(subscription);
    }

    private void goToNext() {
        Intent intent = new Intent(getActivity(), HomeActivity.class);
        getActivity().startActivity(intent);
        getActivity().finish();
    }
}
