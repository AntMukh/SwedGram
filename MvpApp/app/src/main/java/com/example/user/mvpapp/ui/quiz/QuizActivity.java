package com.example.user.mvpapp.ui.quiz;

import android.content.Context;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.user.mvpapp.R;
import com.example.user.mvpapp.ui.common.BaseActivity;
import com.example.user.mvpapp.ui.info.InfoFragment;
import com.example.user.mvpapp.utils.Constants;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Optional;

public class QuizActivity extends BaseActivity
        implements QuizMvpView, NavigationView.OnNavigationItemSelectedListener {

    @Inject
    QuizMvpPresenter<QuizMvpView> mPresenter;

    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.nav_view)
    NavigationView navigationView;

    View cardView;

    Animation changeAnim;
    MediaPlayer bPlayer;

    public static final String LOG_TAG = QuizActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        //inflate cardView
       // inflateCardView();
        getActivityComponent().inject(this);
        setUnBinder(ButterKnife.bind(this));

        changeAnim = AnimationUtils.loadAnimation(this.getApplicationContext(), R.anim.change);
        mPresenter.onAttached(this);
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

    }

    void inflateCardView() {
        LayoutInflater mInflater = getLayoutInflater();
        cardView = mInflater.inflate(R.layout.cardview_layout, null, false);
        cardView.findViewById(R.id.enButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.showAnswer(Constants.EN_BUTTON);
            }
        });
        cardView.findViewById(R.id.ettButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.showAnswer(Constants.ETT_BUTTON);
            }
        });

    }

    public void setQuestion(String word) {

        inflateCardView();
        TextView textView = (TextView) cardView.findViewById(R.id.cardWord);
        textView.setText(word);
        Button enButton = (Button) cardView.findViewById(R.id.enButton);
        enButton.setBackgroundColor(getResources().getColor(R.color.cardview_shadow_start_color));
        Button ettButton = (Button) cardView.findViewById(R.id.ettButton);
        ettButton.setBackgroundColor(getResources().getColor(R.color.cardview_shadow_start_color));
        RelativeLayout cardPlace = ((RelativeLayout) findViewById(R.id.cardFrame));
        cardPlace.removeAllViews();
        cardPlace.addView(cardView);
        cardPlace.startAnimation(changeAnim);
    }

    public void showRed(int answer) {
        View v;
        if (answer == Constants.EN_BUTTON) {
            v = cardView.findViewById(R.id.enButton);
        } else {
            v = cardView.findViewById(R.id.ettButton);
        }
        v.setBackgroundColor(Color.RED);
        Vibrator vibro = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
        vibro.vibrate(100);
        playSound(R.raw.incorrect);
    }

    public void showGreen(int answer) {
        View v;
        if (answer == Constants.EN_BUTTON) {
            v = cardView.findViewById(R.id.enButton);
        } else {
            v = cardView.findViewById(R.id.ettButton);
        }
        v.setBackgroundColor(Color.GREEN);
    }

    public void startNext() {
        new Handler(getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                mPresenter.nextCard();
            }
        }, 900);
    }


    public void closeDrawer() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

    }

    public void startInfoFragment() {
        Log.e(LOG_TAG, "startInfoFragment()");
        //startInfoFragment
        lockDrawer();
        ViewGroup root = (ViewGroup) findViewById(R.id.rootview);
        root.removeAllViews();
        getSupportFragmentManager()
                .beginTransaction()
                .disallowAddToBackStack()
                .setCustomAnimations(R.anim.slide_left, R.anim.slide_right)
                .add(R.id.rootview, InfoFragment.newInstance(), InfoFragment.TAG)
                .commit();
    }

    public void hideInfoFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentByTag(InfoFragment.TAG);
        if (fragment != null) {
            fragmentManager
                    .beginTransaction()
                    .disallowAddToBackStack()
                    .setCustomAnimations(R.anim.slide_left, R.anim.slide_right)
                    .remove(fragment)
                    .commitNow();
            unlockDrawer();
        }

    }

    public void lockDrawer() {
        if (drawer != null)
            drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
    }

    public void unlockDrawer() {
        if (drawer != null)
            drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
    }


    @Override
    public void onBackPressed() {
        Log.e(LOG_TAG, "onBackPressed()");
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentByTag(InfoFragment.TAG);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            mPresenter.closeDrawer();
            return;
        }
        if (fragment == null) {
            super.onBackPressed();
        } else {
            hideInfoFragment();
            mPresenter.onAttached(this);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.quiz, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_info) {
            mPresenter.showInfo();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_info) {
            mPresenter.showInfo();
        } else if (id == R.id.nav_statistics) {

        } else if (id == R.id.nav_account) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void playSound(int _id) {
        if (bPlayer != null) {
            bPlayer.stop();
            bPlayer.release();
        }
        bPlayer = MediaPlayer.create(getApplicationContext(), _id);
        if (bPlayer != null)
            bPlayer.start();
    }

}
