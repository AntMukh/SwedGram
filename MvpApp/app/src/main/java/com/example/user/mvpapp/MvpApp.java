package com.example.user.mvpapp;

import android.app.Application;
import android.util.Log;

import com.example.user.mvpapp.di.component.ApplicationComponent;
import com.example.user.mvpapp.di.component.DaggerApplicationComponent;
import com.example.user.mvpapp.di.module.ApplicationModule;

/**
 * Created by user on 27.08.2017.
 */

public class MvpApp extends Application {

    public static final String LOG_TAG = MvpApp.class.getSimpleName();

    private ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(LOG_TAG, "onCreateApp");
        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this)).build();

        mApplicationComponent.inject(this);
    }

    public ApplicationComponent getComponent() {
        return mApplicationComponent;
    }
}
