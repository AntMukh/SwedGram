package com.example.user.mvpapp.data.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.user.mvpapp.data.DataManagerImpl;
import com.example.user.mvpapp.data.data.WordCard;
import com.example.user.mvpapp.utils.Constants;
import com.example.user.mvpapp.utils.MyUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by user on 29.08.2017.
 */

@Singleton
public class DataBaseManagerImpl implements DataBaseManager {

    DataBaseHelper dbHelper;

    public static final String LOG_TAG = DataManagerImpl.class.getSimpleName();

    @Inject
    public DataBaseManagerImpl(DataBaseHelper dataBaseHelper) {
        dbHelper = dataBaseHelper;
    }

    //@TODO
    // make this method async
    public void initializeFromJSON() {
        JSONArray array = MyUtils.loadJSONFromAsset(dbHelper.getContext().getApplicationContext(), Constants.JSON_FILE_NAME);
        Log.d(LOG_TAG, array.toString());
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(Constants.DATABASE_TABLE, null, null);
        ContentValues cv = new ContentValues();
        for (int i = 0; i < array.length(); i++) {
            try {
                JSONObject obj = array.getJSONObject(i);
                cv.put(Constants.WORD, obj.getString(Constants.WORD));
                cv.put(Constants.ARTICLE, obj.getString(Constants.ARTICLE));
                cv.put(Constants.LVL, obj.getInt(Constants.LVL));
                db.insert(Constants.DATABASE_TABLE, null, cv);
            } catch (Exception e) {
                Log.e(LOG_TAG, e.toString());
            }
        }
        db.close();
    }

    public List<WordCard> getAllDatafromDb() {
        List<WordCard> list = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor c;
        try {
            c = db.query(Constants.DATABASE_TABLE, null, null, null, null, null, null);
        } catch (Error e) {
            Log.e(LOG_TAG, e.toString());
            return null;
        }
        c.moveToFirst();
        for (int i = 0; i < c.getCount(); i++) {
            WordCard wordCard = new WordCard();
            wordCard.word = c.getString(c.getColumnIndex(Constants.WORD));
            wordCard.articel = c.getString(c.getColumnIndex(Constants.ARTICLE));
            wordCard.memlvl = Integer.parseInt(c.getString(c.getColumnIndex(Constants.LVL)));
            list.add(wordCard);
            c.moveToNext();
        }

        return list;
    }
}
