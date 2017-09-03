package com.example.user.mvpapp.di.module;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import com.example.user.mvpapp.di.ActivityContext;
import com.example.user.mvpapp.di.PerActivity;
import com.example.user.mvpapp.ui.info.InfoMvpPresenter;
import com.example.user.mvpapp.ui.info.InfoMvpView;
import com.example.user.mvpapp.ui.info.InfoPresenter;
import com.example.user.mvpapp.ui.quiz.QuizMvpPresenter;
import com.example.user.mvpapp.ui.quiz.QuizMvpView;
import com.example.user.mvpapp.ui.quiz.QuizPresenter;
import com.example.user.mvpapp.ui.splash.SplashMvpPresenter;
import com.example.user.mvpapp.ui.splash.SplashMvpView;
import com.example.user.mvpapp.ui.splash.SplashPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by user on 27.08.2017.
 */

@Module
public class ActivityModule {
    private AppCompatActivity mActivity;

    public ActivityModule(AppCompatActivity activity) {
        this.mActivity = activity;
    }

    @Provides
    @ActivityContext
    Context provideContext() {
        return mActivity;
    }

    @Provides
    AppCompatActivity provideActivity() {
        return mActivity;
    }

    @Provides
    @PerActivity
    SplashMvpPresenter<SplashMvpView> provideSplashPresenter(
            SplashPresenter<SplashMvpView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    QuizMvpPresenter<QuizMvpView> provideQuizPresenter(
            QuizPresenter<QuizMvpView> presenter) {
        return presenter;
    }

    @Provides
    InfoMvpPresenter<InfoMvpView> provideAboutPresenter(
            InfoPresenter<InfoMvpView> presenter) {
        return presenter;
    }
}
