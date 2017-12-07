package com.remberapp;

import android.os.Bundle;

public class HomeActivity extends BaseActivity {

    @Override
    int getContentViewId() {
        return R.layout.activity_home;
    }

    @Override
    int getNavigationMenuItemId() {
        return R.id.navigation_home;
    }
}
