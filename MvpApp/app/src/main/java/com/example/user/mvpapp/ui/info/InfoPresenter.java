package com.example.user.mvpapp.ui.info;

import com.example.user.mvpapp.data.DataManager;
import com.example.user.mvpapp.ui.common.BasePresenter;

import javax.inject.Inject;

/**
 * Created by user on 01.09.2017.
 */

public class InfoPresenter<V extends InfoMvpView> extends BasePresenter<V>
        implements InfoMvpPresenter<V> {
    public static final String LOG_TAG = InfoPresenter.class.getSimpleName();

    @Inject
    public InfoPresenter(DataManager dataManager) {
        super(dataManager);
    }
}
