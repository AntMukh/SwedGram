package com.example.user.mvpapp.ui.splash;

/**
 * Created by user on 27.08.2017.
 */

import android.util.Log;

import com.example.user.mvpapp.data.DataManager;
import com.example.user.mvpapp.ui.common.BasePresenter;

import javax.inject.Inject;

import static com.example.user.mvpapp.utils.MyUtils.LOG_TAG;

public class SplashPresenter<V extends SplashMvpView> extends BasePresenter<V>
        implements SplashMvpPresenter<V> {

    @Inject
    public SplashPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void onAttached(V v) {
        super.onAttached(v);
        Log.e(LOG_TAG, "onAttach");
        //decide what to do next.
        //if first run - seed words
        //else start quiz
        getDataManager().initializeDataBase();
        getMvpView().startQuizActivity();
    }
}
