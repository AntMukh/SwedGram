package com.example.user.mvpapp.ui.quiz;

import android.util.Log;

import com.example.user.mvpapp.data.DataManager;
import com.example.user.mvpapp.data.data.WordCard;
import com.example.user.mvpapp.ui.common.BasePresenter;
import com.example.user.mvpapp.utils.Constants;

import java.util.List;

import javax.inject.Inject;


/**
 * Created by user on 31.08.2017.
 */

public class QuizPresenter<V extends QuizMvpView> extends BasePresenter<V>
        implements QuizMvpPresenter<V> {

    DataManager mDataManager;

    public static final String LOG_TAG = QuizPresenter.class.getSimpleName();

    //showing algorithm implementation
    //@TODO
    //implement more sophisticated algorithm
    public class State {
        public List<WordCard> cardList;
        int lineToShow;

        public State(List<WordCard> list) {
            cardList = list;
            lineToShow = 0;
        }

        public WordCard getCurrentCard() {
            return cardList.get(lineToShow);
        }

        public WordCard getNextCard() {
            WordCard card = cardList.get(lineToShow);
            return card;
        }

        public void goToNext() {
            lineToShow++;
            if (lineToShow >= cardList.size()) {
                lineToShow = 0;
            }
        }
    }

    State state;


    @Inject
    public QuizPresenter(DataManager dataManager) {
        super(dataManager);
        mDataManager = dataManager;
        state = new State(mDataManager.getAllDatafromDb());
        for (int i = 0; i < state.cardList.size(); i++) {
            Log.e(LOG_TAG, " word =" + state.cardList.get(i).word.toString());
        }
        Log.e(LOG_TAG, "alldatafromdb" + mDataManager.getAllDatafromDb().toString());

    }

    public void onAttached(V v) {
        super.onAttached(v);
        nextCard();
    }

    public void nextCard() {
        getMvpView().setQuestion(state.getNextCard().word);
    }

    public void showAnswer(int answer) {
        if (
                (state.getCurrentCard().articel.equals(Constants.EN_ARTICEL) && answer == Constants.EN_BUTTON) ||
                        (state.getCurrentCard().articel.equals(Constants.ETT_ARTICEL) && answer == Constants.ETT_BUTTON)) {
            getMvpView().showGreen(answer);
            Log.e(LOG_TAG, "show green " + answer);
        } else {
            getMvpView().showRed(answer);
            Log.e(LOG_TAG, "show red" + answer);
        }
        state.goToNext();

        getMvpView().startNext();
    }

    public void closeDrawer() {
        getMvpView().closeDrawer();
    }

    public void showInfo() {
        closeDrawer();
        getMvpView().startInfoFragment();
    }

}
