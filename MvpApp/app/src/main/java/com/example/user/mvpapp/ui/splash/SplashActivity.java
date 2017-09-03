package com.example.user.mvpapp.ui.splash;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.user.mvpapp.R;
import com.example.user.mvpapp.ui.common.BaseActivity;
import com.example.user.mvpapp.ui.quiz.QuizActivity;

import javax.inject.Inject;


public class SplashActivity extends BaseActivity implements SplashMvpView {

    public static final String LOG_TAG = SplashActivity.class.getSimpleName();

    @Inject
    SplashMvpPresenter<SplashMvpView> mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getActivityComponent().inject(this);
        Log.e(LOG_TAG, "mPresenter=" + mPresenter.toString());
        mPresenter.onAttached(SplashActivity.this);
    }

    public void startQuizActivity() {
        Intent intent = new Intent(this, QuizActivity.class);
        startActivity(intent);
        finish();
    }
}
