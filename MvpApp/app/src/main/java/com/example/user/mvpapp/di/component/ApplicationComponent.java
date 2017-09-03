package com.example.user.mvpapp.di.component;

import android.app.Application;
import android.content.Context;

import com.example.user.mvpapp.MvpApp;
import com.example.user.mvpapp.data.DataManager;
import com.example.user.mvpapp.di.ApplicationContext;
import com.example.user.mvpapp.di.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by user on 27.08.2017.
 */

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(MvpApp app);

    @ApplicationContext
    Context context();

    Application application();

    DataManager getDataManager();

    //DataBaseManager getDataBaseManager();


}