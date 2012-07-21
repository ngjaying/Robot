package com.gmail.ngjaying.robot;

import android.app.Activity;
import android.media.AudioManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
	private static final String HAS_STARTED = "hasStarted";
	private static final String TAG = "com.gmail.ngjaying.robot.MainActivity";
	private boolean mStarted;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //TODO should check if service started in onResume
        if(savedInstanceState!=null)
        	mStarted = savedInstanceState.getBoolean(HAS_STARTED);
        else
        	mStarted = false;
    }

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		outState.putBoolean(HAS_STARTED, mStarted);
		super.onSaveInstanceState(outState);
	}

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    public void startRunning(View view){
    	Log.v(TAG, "button clicked");
    	Button startButton = (Button) view;
    	AudioManager audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
    	if(!mStarted){
    		startButton.setText(R.string.main_stop_button);
        	audioManager.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
    	}else{
    		startButton.setText(R.string.main_start_button);
        	audioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
    	}
    	mStarted = !mStarted;
    }

    
}
