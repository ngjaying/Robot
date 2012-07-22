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
import com.gmail.ngjaying.robot.model.conditions.AlarmCondition;
import com.gmail.ngjaying.robot.model.conditions.RingtoneBehavior;

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
    	//TODO the behaviors must be set through UI. Currently, this is the 2 behaviors I need. We also need a serialized method to save these.
    	Calendar cal = Calendar.getInstance();
    	cal.set(Calendar.HOUR_OF_DAY, 9);
    	cal.set(Calendar.MINUTE, 0);
    	ICondition condition1 = new AlarmCondition(cal.getTimeInMillis(), AlarmManager.INTERVAL_DAY);
    	cal.set(Calendar.HOUR_OF_DAY, 18);
    	ICondition condition2 = new AlarmCondition(cal.getTimeInMillis(), AlarmManager.INTERVAL_DAY);
    	cal.set(Calendar.HOUR_OF_DAY, 13);
    	cal.set(Calendar.MINUTE, 41);
    	ICondition condition3 = new AlarmCondition(cal.getTimeInMillis(), AlarmManager.INTERVAL_DAY);
    	mConditions.add(condition1);
    	mConditions.add(condition2);
    	mConditions.add(condition3);
    	
    	IBehavior behavior1 = new RingtoneBehavior(AudioManager.RINGER_MODE_VIBRATE);
    	IBehavior behavior2 = new RingtoneBehavior(AudioManager.RINGER_MODE_NORMAL);
//    	IBehavior behavior2 = new AbstractBehavior(){
//
//			@Override
//			public void execute() {
//				Log.v(Constants.MESSAGE_TAG, "Auto sync behavior triggered");
//				if(!ContentResolver.getMasterSyncAutomatically())
//	        		ContentResolver.setMasterSyncAutomatically(true);
//			}
//    		
//    	};
    	mBehaviors.add(behavior1);
    	mBehaviors.add(behavior2);
    	
    	condition3.addBehavior(behavior1);
    	condition3.setup(this);
    	
    	if(!mStarted){
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
