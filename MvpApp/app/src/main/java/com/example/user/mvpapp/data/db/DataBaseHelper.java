package com.example.user.mvpapp.data.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.user.mvpapp.di.ApplicationContext;
import com.example.user.mvpapp.utils.Constants;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by user on 29.08.2017.
 */

@Singleton
public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String LOG_TAG = DataBaseHelper.class.getSimpleName();

    public Context cntxt;

    @Inject
    public DataBaseHelper(@ApplicationContext Context context) {
        super(context, Constants.DB_NAME, null, Constants.DB_VERSION);
        cntxt = context;
        Log.e(LOG_TAG, "DataBaseHelper  OnCreate()");
    }

    public void onCreate(SQLiteDatabase db) {
        //create db
        Log.e(LOG_TAG, "--- onCreate database ---");
        // create table with a fields
        db.execSQL("create table " + Constants.DATABASE_TABLE + " ("
                + Constants.ID + " " + "integer primary key autoincrement,"
                + Constants.WORD + ", "
                + Constants.ARTICLE + ", "
                + Constants.LVL
                + ");");

    }

    public Context getContext() {
        return cntxt;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
