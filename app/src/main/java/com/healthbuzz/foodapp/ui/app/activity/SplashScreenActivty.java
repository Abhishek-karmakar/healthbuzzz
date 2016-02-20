package com.healthbuzz.foodapp.ui.app.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import heathbuzz.app.healthapp.R;

/**
 * Created by vassharma on 2/19/16.
 */
public class SplashScreenActivty extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        int SPLASH_TIME_OUT = 3000;
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                startActivity(MainActivity.newInstance(SplashScreenActivty.this));
            }
        }, SPLASH_TIME_OUT);
    }

    /**
     * Initialize the RecallServiceManager instance before using
     */
    private void initializeRecallServiceManager() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
