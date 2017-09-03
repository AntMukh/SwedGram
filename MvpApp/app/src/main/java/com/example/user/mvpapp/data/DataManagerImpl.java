package com.example.user.mvpapp.data;

import android.content.Context;

import com.example.user.mvpapp.data.data.WordCard;
import com.example.user.mvpapp.data.db.DataBaseManager;
import com.example.user.mvpapp.data.pref.PrefManager;
import com.example.user.mvpapp.di.ApplicationContext;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by user on 28.08.2017.
 */

@Singleton
public class DataManagerImpl implements DataManager {

    private final PrefManager mPrefManager;
    private final DataBaseManager mDataBaseManager;
    private Context cntxt;

    public static final String LOG_TAG = DataManagerImpl.class.getSimpleName();

    @Inject
    public DataManagerImpl(@ApplicationContext Context context,
                           PrefManager prefManager,
                           DataBaseManager dbManager
    ) {
        cntxt = context;
        mPrefManager = prefManager;
        mDataBaseManager = dbManager;
    }

    public void initializeDataBase() {
        //ifnotfirstlaunch
        if (mPrefManager.isFirstRun()) {
            mDataBaseManager.initializeFromJSON();
        } else {
            //do nothing
        }
    }

    public void initializeFromJSON() {
        mDataBaseManager.initializeFromJSON();
    }

    @Override
    public List<WordCard> getAllDatafromDb() {
        return mDataBaseManager.getAllDatafromDb();
    }

}
