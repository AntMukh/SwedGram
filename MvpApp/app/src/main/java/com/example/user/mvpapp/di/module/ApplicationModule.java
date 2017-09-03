package com.example.user.mvpapp.di.module;

import android.app.Application;
import android.content.Context;

import com.example.user.mvpapp.data.DataManager;
import com.example.user.mvpapp.data.DataManagerImpl;
import com.example.user.mvpapp.data.db.DataBaseManager;
import com.example.user.mvpapp.data.db.DataBaseManagerImpl;
import com.example.user.mvpapp.data.pref.PrefManager;
import com.example.user.mvpapp.data.pref.PrefManagerImpl;
import com.example.user.mvpapp.di.ApplicationContext;
import com.example.user.mvpapp.di.PrefName;
import com.example.user.mvpapp.utils.Constants;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


/**
 * Created by user on 27.08.2017.
 */

@Module
@Singleton
public class ApplicationModule {

    private final Application mApplication;

    public ApplicationModule(Application application) {
        mApplication = application;
    }

    @Provides
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return mApplication;
    }

    @Provides
    @PrefName
    String providePreferenceName() {
        return Constants.PREF_NAME;
    }

    @Provides
    @Singleton
    DataManager provideDataManager(DataManagerImpl dataManager) {
        return dataManager;
    }

    @Provides
    @Singleton
    PrefManager providePrefManager(PrefManagerImpl prefManager) {
        return prefManager;
    }

    @Provides
    @Singleton
    DataBaseManager provideDataBaseManager(DataBaseManagerImpl dataBaseManager) {
        return dataBaseManager;
    }


}
