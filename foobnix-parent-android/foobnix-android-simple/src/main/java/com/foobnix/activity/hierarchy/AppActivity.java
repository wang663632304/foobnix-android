package com.foobnix.activity.hierarchy;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import com.foobnix.FoobnixApplication;
import com.foobnix.mediaengine.MediaEngineState;

public abstract class AppActivity extends Activity {
    protected FoobnixApplication app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        app = (FoobnixApplication) getApplication();
    }

    public abstract void onActivate(Activity activity);

    public abstract void onUpdateUIListener(MediaEngineState state);

    BroadcastReceiver updateProggressReciver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            MediaEngineState state = (MediaEngineState) intent.getSerializableExtra("MediaEngineState");
            if (state != null) {
                onUpdateUIListener(state);
            }
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(updateProggressReciver, new IntentFilter("UpdateProggress"));
    };

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(updateProggressReciver);
    };



}
