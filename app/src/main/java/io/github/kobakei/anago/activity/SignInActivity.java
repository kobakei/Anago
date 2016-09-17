package io.github.kobakei.anago.activity;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import io.github.kobakei.anago.R;
import io.github.kobakei.anago.databinding.SignInActivityBinding;

/**
 * サインイン画面
 */
public class SignInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SignInActivityBinding binding = DataBindingUtil.setContentView(this, R.layout.sign_in_activity);
    }
}
