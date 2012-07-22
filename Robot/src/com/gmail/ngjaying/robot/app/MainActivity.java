package com.gmail.ngjaying.robot.app;

import java.util.ArrayList;
import java.util.Calendar;

import android.app.Activity;
import android.app.AlarmManager;
import android.media.AudioManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import com.gmail.najaying.robot.utils.Constants;
import com.gmail.ngjaying.robot.R;
import com.gmail.ngjaying.robot.model.IBehavior;
import com.gmail.ngjaying.robot.model.ICondition;
import com.gmail.ngjaying.robot.model.behaviors.AutoSyncBehavior;
import com.gmail.ngjaying.robot.model.behaviors.RingtoneBehavior;
import com.gmail.ngjaying.robot.model.conditions.AlarmCondition;
import com.gmail.ngjaying.robot.model.conditions.WifiCondition;

public class MainActivity extends Activity {
	private static final String HAS_STARTED = "hasStarted";
	private boolean mStarted;
	private ArrayList<IBehavior> mBehaviors;
	private ArrayList<ICondition> mConditions;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //TODO should check if service started in onResume
        if(savedInstanceState!=null)
        	mStarted = savedInstanceState.getBoolean(HAS_STARTED);
        else
        	mStarted = false;
        //TODO Read the behavious, conditions and connections
        mBehaviors = new ArrayList<IBehavior>();
        mConditions = new ArrayList<ICondition>();
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
    	Log.v(Constants.MESSAGE_TAG, "button clicked");
    	Button startButton = (Button) view;
    	
    	if(!mStarted){
    		//TODO the behaviors must be set through UI. Currently, this is the 2 behaviors I need. We also need a serialized method to save these.
        	Calendar cal = Calendar.getInstance();
        	cal.set(Calendar.HOUR_OF_DAY, 9);
        	cal.set(Calendar.MINUTE, 0);
        	ICondition condition1 = new AlarmCondition(cal.getTimeInMillis(), AlarmManager.INTERVAL_DAY);
        	cal.set(Calendar.HOUR_OF_DAY, 16);
        	ICondition condition2 = new AlarmCondition(cal.getTimeInMillis(), AlarmManager.INTERVAL_DAY);
        	ICondition condition3 = new WifiCondition(true);
        	ICondition condition4 = new WifiCondition(false);
        	mConditions.add(condition1);
        	mConditions.add(condition2);
        	
        	IBehavior behavior1 = new RingtoneBehavior(AudioManager.RINGER_MODE_VIBRATE);
        	IBehavior behavior2 = new RingtoneBehavior(AudioManager.RINGER_MODE_NORMAL);
        	IBehavior behavior3 = new AutoSyncBehavior(true);
        	IBehavior behavior4 = new AutoSyncBehavior(false);
        	mBehaviors.add(behavior1);
        	mBehaviors.add(behavior2);
        	mBehaviors.add(behavior3);
        	mBehaviors.add(behavior4);
        	//Setup conditions
        	condition1.addBehavior(behavior1);
        	condition1.setup(this);
        	condition2.addBehavior(behavior2);
        	condition2.setup(this);
        	condition3.addBehavior(behavior3);
        	condition3.setup(this);
        	condition4.addBehavior(behavior4);
        	condition4.setup(this);
        	
        	
    		startButton.setText(R.string.main_stop_button);
    	}else{
    		//TODO stop all services here
    		startButton.setText(R.string.main_start_button);
    	}
    	mStarted = !mStarted;
    }

	public ArrayList<IBehavior> getmBehaviors() {
		return mBehaviors;
	}

}
