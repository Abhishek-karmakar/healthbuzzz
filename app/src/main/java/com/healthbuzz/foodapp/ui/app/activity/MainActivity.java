package com.healthbuzz.foodapp.ui.app.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.healthbuzz.foodapp.adapter.NavigationDrawerListAdapter;
import com.healthbuzz.foodapp.ui.app.fragment.AboutUsFragment;
import com.healthbuzz.foodapp.ui.app.fragment.ContactUsFragment;
import com.healthbuzz.foodapp.ui.app.fragment.FavoritesFragment;
import com.healthbuzz.foodapp.ui.app.fragment.FoodMenuFragment;
import com.healthbuzz.foodapp.ui.app.fragment.MyOrdersFragment;
import com.healthbuzz.foodapp.ui.app.fragment.PaymentFragment;
import com.healthbuzz.foodapp.ui.app.fragment.ReferUsFragment;
import com.healthbuzz.foodapp.ui.app.model.SideNavigationItem;

import java.util.ArrayList;
import java.util.List;

import heathbuzz.app.healthapp.R;

public class MainActivity extends BaseActivity {

    private static final int SCROLL_POSITION = 0;
    private static final int BACKSTACK_FLAG = 0;
    private static final int DEFAULT_IMAGE_RESOURCE = 0;
    private static Context mContext;
    private final List<SideNavigationItem> sideNavigationItemList = new ArrayList<>();
    private ListView mNavigationDrawerListView;

    public static Intent newInstance(Context context) {
        return new Intent(context, MainActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });z

        mDrawerLayout = (DrawerLayout) findViewById(R.id.activity_main_drawer);
        mToolBar = (Toolbar) findViewById(R.id.common_header);
        setSupportActionBar(mToolBar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolBar, R.string.open,
                R.string.close);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();

        mContext = this;

        mNavigationDrawerListView = (ListView) findViewById(R.id.activity_main_navigation_listview);

        setSideNavigationItems();

        NavigationDrawerListAdapter mNavigationDrawerListAdapter = new NavigationDrawerListAdapter(this, sideNavigationItemList);
        mNavigationDrawerListView.setAdapter(mNavigationDrawerListAdapter);

        /*Select MyFamily tab by default*/
        selectItem(LeftDrawerPosition.FOOD_MENU);
        mNavigationDrawerListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectItem(position);
            }
        });

    }

    /**
     * Desc: get position of item clicked from listview and open its respective fragment.
     *
     * @param position
     */
    private void selectItem(int position) {

        Fragment fragment = null;
        Bundle bundle = new Bundle();
        switch (position) {
            case LeftDrawerPosition.FOOD_MENU:
                fragment = new FoodMenuFragment();
                setPageTitle(getResources().getString(R.string.food_menu));
                break;

            case LeftDrawerPosition.MY_ORDERS:
                fragment = new MyOrdersFragment();
                setPageTitle(getResources().getString(R.string.my_order));
                break;

            case LeftDrawerPosition.FAVORITES:
                fragment = new FavoritesFragment();
                setPageTitle(getResources().getString(R.string.favorites));
                break;

            case LeftDrawerPosition.PAYMENT:
                fragment = new PaymentFragment();
                setPageTitle(getResources().getString(R.string.payment));
                break;

            case LeftDrawerPosition.REFER_US:
                fragment = new ReferUsFragment();
                setPageTitle(getResources().getString(R.string.refer_us));
                break;

            case LeftDrawerPosition.CONTACT_US:
                fragment = new ContactUsFragment();
                setPageTitle(getResources().getString(R.string.contact_us));
                break;

            case LeftDrawerPosition.ABOUT_US:
                fragment = new AboutUsFragment();
                setPageTitle(getResources().getString(R.string.about_us));
                break;

            default:
                break;
        }
        if (fragment != null) {
            replaceFragment(fragment, bundle);
        }
        mNavigationDrawerListView.setItemChecked(position, true);
        mNavigationDrawerListView.setSelector(R.drawable.side_nav_background_selector);
        mNavigationDrawerListView.smoothScrollToPosition(SCROLL_POSITION);
        mDrawerLayout.closeDrawers();
    }

    /**
     * Loads fragment in frame container
     *
     * @param fragment
     * @param bundle
     */
    private void replaceFragment(Fragment fragment, Bundle bundle) {

        String backStateName = fragment.getClass().getName();
        fragment.setArguments(bundle);

        FragmentManager manager = getSupportFragmentManager();
        boolean fragmentPopped = manager.popBackStackImmediate(backStateName, BACKSTACK_FLAG);
        if (!fragmentPopped) { //fragment not in back stack, create it.
            FragmentTransaction ft = manager.beginTransaction();
            ft.setCustomAnimations(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
            ft.replace(R.id.activity_landing_page_container, fragment);
            ft.commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /**
     * set item to SideNavigationItem for side navigation drawer
     */
    private void setSideNavigationItems() {
        sideNavigationItemList.add(new SideNavigationItem(getResources().getString(R.string.food_menu), DEFAULT_IMAGE_RESOURCE, false));
        sideNavigationItemList.add(new SideNavigationItem(getResources().getString(R.string.my_order), DEFAULT_IMAGE_RESOURCE, false));
        sideNavigationItemList.add(new SideNavigationItem(getResources().getString(R.string.favorites), DEFAULT_IMAGE_RESOURCE, false));
        sideNavigationItemList.add(new SideNavigationItem(getResources().getString(R.string.payment), DEFAULT_IMAGE_RESOURCE, false));
        sideNavigationItemList.add(new SideNavigationItem(getResources().getString(R.string.refer_us), DEFAULT_IMAGE_RESOURCE, false));
        sideNavigationItemList.add(new SideNavigationItem(getResources().getString(R.string.contact_us), DEFAULT_IMAGE_RESOURCE, false));
        sideNavigationItemList.add(new SideNavigationItem(getResources().getString(R.string.about_us), DEFAULT_IMAGE_RESOURCE, false));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Constants for Side Navigation Menu
     */
    private static class LeftDrawerPosition {
        private final static int FOOD_MENU = 0;
        private final static int MY_ORDERS = 1;
        private final static int FAVORITES = 2;
        private final static int PAYMENT = 3;
        private final static int REFER_US = 4;
        private final static int CONTACT_US = 5;
        private final static int ABOUT_US = 6;
    }
}
