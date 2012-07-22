package com.gmail.ngjaying.robot.model.conditions;

import java.util.ArrayList;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.gmail.najaying.robot.utils.Constants;
import com.gmail.ngjaying.robot.model.IBehavior;
import com.gmail.ngjaying.robot.model.ICondition;

public class AlarmCondition implements ICondition {

	private long mTrigerAtMillis;
	private long mIntervalMillis;
	private ArrayList<IBehavior> mBehaviors;
	private PendingIntent mIntent;
	
	public AlarmCondition(long trigerAtMillis, long intervalMillis) {
		this.mTrigerAtMillis = trigerAtMillis;
		this.mIntervalMillis = intervalMillis;
	}

	@Override
	public void setup(Context ctx) {
		Log.v(Constants.ALARMCONDITION_EXTRA, "setup alarm condition");
		Intent intent = new Intent(ctx, AlarmReceiver.class);
		intent.putExtra(Constants.ALARMCONDITION_EXTRA, getBehaviors());
		mIntent = PendingIntent.getBroadcast(ctx, 3369043, intent, PendingIntent.FLAG_UPDATE_CURRENT);

		// Get the AlarmManager service
		AlarmManager am = (AlarmManager) ctx.getSystemService(Context.ALARM_SERVICE);
		if(mIntervalMillis>0)
			am.setRepeating(AlarmManager.RTC_WAKEUP, mTrigerAtMillis, mIntervalMillis, mIntent);	
		else
			am.set(AlarmManager.RTC_WAKEUP, mTrigerAtMillis, mIntent);
			
	}

	@Override
	public void cancel(Context ctx) {
		AlarmManager am = (AlarmManager) ctx.getSystemService(Context.ALARM_SERVICE);
		am.cancel(mIntent);
	}

	@Override
	public ArrayList<IBehavior> getBehaviors() {
		return mBehaviors;
	}
	
	@Override
	public void addBehavior(IBehavior behavior){
		if(null == mBehaviors) mBehaviors = new ArrayList<IBehavior>();
		mBehaviors.add(behavior);
	}

}
