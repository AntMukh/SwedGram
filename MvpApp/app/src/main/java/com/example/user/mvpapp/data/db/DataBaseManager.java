package com.example.user.mvpapp.data.db;

import com.example.user.mvpapp.data.data.WordCard;

import java.util.List;

/**
 * Created by user on 29.08.2017.
 */

public interface DataBaseManager {
    public void initializeFromJSON();

    public List<WordCard> getAllDatafromDb();
}
