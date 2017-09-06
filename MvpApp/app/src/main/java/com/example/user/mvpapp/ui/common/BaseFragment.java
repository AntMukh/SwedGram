package com.example.user.mvpapp.ui.common;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.example.user.mvpapp.di.component.ActivityComponent;

import butterknife.Unbinder;

/**
 * Created by user on 01.09.2017.
 */

public abstract class BaseFragment extends Fragment implements MvpView {

    private BaseActivity mActivity;

    private Unbinder mUnBinder;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof BaseActivity) {
            BaseActivity activity = (BaseActivity) context;
            this.mActivity = activity;
            activity.onFragmentAttached();
        }
    }

    public ActivityComponent getActivityComponent() {
        if (mActivity != null) {
            return mActivity.getActivityComponent();
        }
        return null;
    }

    public BaseActivity getBaseActivity() {
        return mActivity;
    }

    public void setUnBinder(Unbinder unBinder) {
        mUnBinder = unBinder;
    }

}
