package com.healthbuzz.foodapp.ui.app.activity;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import heathbuzz.app.healthapp.R;

/**
 * Created by vassharma on 2/20/16.
 */
public class BaseActivity extends AppCompatActivity{

    protected Toolbar mToolBar;
    protected DrawerLayout mDrawerLayout;
    protected ActionBarDrawerToggle mDrawerToggle;
    /**
     * Supply title string to toolbar
     *
     * @param title
     */
    public void setPageTitle(String title) {
        if (mToolBar != null) {
            ((TextView) findViewById(R.id.toolbar_title)).setText(title);
        }
    }
}
