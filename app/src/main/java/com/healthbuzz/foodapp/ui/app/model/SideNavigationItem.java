package com.healthbuzz.foodapp.ui.app.model;

/**
 * FoodPanda
 * Created by vassharma on 2/19/16.
 * Copyright (c) 2016 FoodPanda. All rights reserved.
 * <p/>
 * SideNavigationItem to hold object of NavigationView for side navigation
 */
public class SideNavigationItem {

    private final String mItemTitle;
    private final int mItemImage;
    private final boolean mIsHeader;

    public SideNavigationItem(String title, int imageResource, boolean isHeader) {
        this.mItemTitle = title;
        this.mItemImage = imageResource;
        this.mIsHeader = isHeader;
    }

    public String getItemTitle() {
        return mItemTitle;
    }

    public int getItemImage() {
        return mItemImage;
    }

    public boolean getIsItemIsHeader() {
        return mIsHeader;
    }

}