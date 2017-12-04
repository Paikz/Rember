package com.remberapp;

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
                Bundle args = new Bundle();
                args.putString("OptionName", generalStr);
                GeneralOptions fragment = new GeneralOptions();
                fragment.setArguments(args);
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.ConstLayout, fragment).addToBackStack(null);
                transaction.commit();
                break;
            case R.id.theme:
                break;
            case R.id.notifications:
        }
    }
}
