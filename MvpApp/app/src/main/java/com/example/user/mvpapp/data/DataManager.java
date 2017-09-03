package com.example.user.mvpapp.data;

import com.example.user.mvpapp.data.db.DataBaseManager;

/**
 * Created by user on 28.08.2017.
 */

public interface DataManager extends DataBaseManager {
    public void initializeDataBase();

}
