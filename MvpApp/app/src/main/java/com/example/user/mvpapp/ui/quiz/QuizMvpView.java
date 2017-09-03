package com.example.user.mvpapp.ui.quiz;

import com.example.user.mvpapp.ui.common.MvpView;

/**
 * Created by user on 31.08.2017.
 */

public interface QuizMvpView extends MvpView {
    void setQuestion(String word);

    void showRed(int answer);

    void showGreen(int answer);

    void startNext();

    void closeDrawer();

    void startInfoFragment();

}
