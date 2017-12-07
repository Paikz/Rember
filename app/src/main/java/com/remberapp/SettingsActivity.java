package com.remberapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.remberapp.Options.OptionAboutActivity;
import com.remberapp.Options.OptionChangeNumberActivity;
import com.remberapp.Options.OptionGeneralActivity;
import com.remberapp.Options.OptionNotificationActivity;
import com.remberapp.Options.OptionThemeActivity;

public class SettingsActivity extends BaseActivity {
    private String generalStr = "General";
    private String themeStr = "General";
    private String notiStr = "General";
    private String chnumbStr = "General";
    private String aboutStr = "General";

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
                startActivity(intentBundle);
                break;
            case R.id.theme:
                intentBundle = new Intent(this, OptionThemeActivity.class);
                startActivity(intentBundle);
                break;
            case R.id.notifications:
                intentBundle = new Intent(this, OptionNotificationActivity.class);
                startActivity(intentBundle);
                break;
            case R.id.change_number:
                intentBundle = new Intent(this, OptionChangeNumberActivity.class);

                startActivity(intentBundle);
                break;
            case R.id.about:
                intentBundle = new Intent(this, OptionAboutActivity.class);
                startActivity(intentBundle);
                break;
        }
    }
}
