package com.example.user.mvpapp.ui.common;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.user.mvpapp.MvpApp;
import com.example.user.mvpapp.di.component.ActivityComponent;
import com.example.user.mvpapp.di.component.DaggerActivityComponent;
import com.example.user.mvpapp.di.module.ActivityModule;

import butterknife.Unbinder;


public abstract class BaseActivity extends AppCompatActivity
        implements MvpView {

    private ActivityComponent mActivityComponent;

    private Unbinder mUnBinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityComponent = DaggerActivityComponent.builder()
                .activityModule(new ActivityModule(this))
                .applicationComponent(((MvpApp) getApplication()).getComponent())
                .build();
    }

    public ActivityComponent getActivityComponent() {
        return mActivityComponent;
    }


    public void onFragmentAttached() {

    }

    public void onFragmentDetached(String tag) {
    }

    public void setUnBinder(Unbinder unBinder) {
        mUnBinder = unBinder;
    }
}
