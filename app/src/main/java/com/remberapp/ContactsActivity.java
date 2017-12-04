package com.remberapp;

public class ContactsActivity extends BaseActivity {

    @Override
    int getContentViewId() {
        return R.layout.activity_contacts;
    }

    @Override
    int getNavigationMenuItemId() {
        return R.id.navigation_contacts;
    }
}
