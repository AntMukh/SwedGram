package com.example.user.mvpapp.utils;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by user on 29.08.2017.
 */

public final class MyUtils {
    public static final String LOG_TAG = "Utils";

    private MyUtils() {
    }

    public static JSONArray loadJSONFromAsset(Context context, String jsonFileName) {
        String json;
        try {

            InputStream is = context.getAssets().open(jsonFileName);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            //ex.printStackTrace();
            Log.e(LOG_TAG, ex.toString());
            return null;
        }
        JSONArray array;
        try {
            array = new JSONArray(json);
        } catch (Exception e) {
            Log.e(LOG_TAG, e.toString());
            return null;
        }
        return array;
    }
}
