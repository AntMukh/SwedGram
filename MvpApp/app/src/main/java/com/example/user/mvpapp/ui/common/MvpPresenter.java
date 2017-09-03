package com.example.user.mvpapp.ui.common;

/**
 * Created by user on 27.08.2017.
 */

public interface MvpPresenter<V extends MvpView> {
    public void onAttached(V v);
}
