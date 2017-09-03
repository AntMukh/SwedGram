package com.example.user.mvpapp.ui.common;

import com.example.user.mvpapp.data.DataManager;

import javax.inject.Inject;

/**
 * Created by user on 27.08.2017.
 */

public class BasePresenter<V extends MvpView> implements MvpPresenter<V> {
    private V mMvpView;
    DataManager mDataManager;

    @Inject
    public BasePresenter(DataManager dataManager) {
        mDataManager = dataManager;
    }

    public void onAttached(V v) {
        mMvpView = v;
    }

    public V getMvpView() {
        return mMvpView;
    }

    public DataManager getDataManager() {
        return mDataManager;
    }
}
