package com.remberapp;

public class AddReminderActivity extends BaseActivity {

    @Override
    int getContentViewId() {
        return R.layout.activity_add_reminder;
    }

    @Override
    int getNavigationMenuItemId() {
        return R.id.navigation_add_reminder;
    }
}
