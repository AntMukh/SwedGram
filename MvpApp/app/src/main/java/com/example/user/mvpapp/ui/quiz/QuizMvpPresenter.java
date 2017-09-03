package com.example.user.mvpapp.ui.quiz;

import com.example.user.mvpapp.ui.common.MvpPresenter;

/**
 * Created by user on 31.08.2017.
 */

public interface QuizMvpPresenter<V extends QuizMvpView> extends MvpPresenter<V> {
    void showAnswer(int answer);

    void nextCard();

    void closeDrawer();

    void showInfo();


}
