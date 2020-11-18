package com.example.scheduledlogs.view.activity;

import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;

import com.example.scheduledlogs.view.fragment.ScheduledLogsFragment;

public class ScheduledLogsActivity extends SingleFragmentActivity {

    public static Intent newIntent(Context context) {
        return new Intent(context, ScheduledLogsActivity.class);
    }

    @Override
    public Fragment createFragment() {
        return ScheduledLogsFragment.newInstance();
    }


}