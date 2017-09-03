package com.example.user.mvpapp.data.pref;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.user.mvpapp.di.ApplicationContext;
import com.example.user.mvpapp.di.PrefName;
import com.example.user.mvpapp.utils.Constants;

import javax.inject.Inject;
import javax.inject.Singleton;

import static com.example.user.mvpapp.utils.MyUtils.LOG_TAG;

/**
 * Created by user on 28.08.2017.
 */

@Singleton
public class PrefManagerImpl implements PrefManager {

    private final SharedPreferences mPrefs;

    @Inject
    public PrefManagerImpl(@ApplicationContext Context context,
                           @PrefName String prefName
    ) {
        mPrefs = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
    }

    //@TODO
    // implement code upgrade support
    public boolean isFirstRun() {
        if (mPrefs.getBoolean(Constants.IS_FIRST_RUN, true)) {
            Log.e(LOG_TAG, "first application run");
            mPrefs.edit().putBoolean(Constants.IS_FIRST_RUN, false).commit();
            return true;
        } else {
            return false;
        }
    }
}
