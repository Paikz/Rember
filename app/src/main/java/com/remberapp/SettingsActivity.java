package com.remberapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

public class SettingsActivity extends BaseActivity {
    private String generalStr = "General";

    @Override
    int getContentViewId() {
        return R.layout.activity_settings;
    }

    @Override
    int getNavigationMenuItemId() {
        return R.id.navigation_settings;
    }

    public void click(View v) {
        switch(v.getId()) {
            case R.id.general:
                Intent intentBundle = new Intent(this, OptionGeneralActivity.class);
                Bundle args = new Bundle();
                args.putString("Name", generalStr);
                intentBundle.putExtras(args);
                startActivity(intentBundle);
                break;
            case R.id.theme:
                break;
            case R.id.notifications:
        }
    }
}
