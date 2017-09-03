package com.example.user.mvpapp.di.component;

import com.example.user.mvpapp.di.PerActivity;
import com.example.user.mvpapp.di.module.ActivityModule;
import com.example.user.mvpapp.ui.info.InfoFragment;
import com.example.user.mvpapp.ui.quiz.QuizActivity;
import com.example.user.mvpapp.ui.splash.SplashActivity;

import dagger.Component;

/**
 * Created by user on 27.08.2017.
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(SplashActivity activity);

    void inject(QuizActivity activity);

    void inject(InfoFragment fragment);

}
