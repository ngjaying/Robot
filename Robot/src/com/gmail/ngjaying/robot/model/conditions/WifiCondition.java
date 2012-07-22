package com.gmail.ngjaying.robot.model.conditions;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import com.gmail.najaying.robot.utils.Constants;
import com.gmail.ngjaying.robot.model.AbstractCondition;
import com.gmail.ngjaying.robot.model.IBehavior;

public class WifiCondition extends AbstractCondition implements Callback {
	private static final String WIFI_THREAD = "WifiThread";
	private boolean mEnableWifi;
	private BroadcastReceiver mReceiver;
	
	public WifiCondition(boolean mEnableWifi) {
		super();
		this.mEnableWifi = mEnableWifi;
	}

	@Override
	public void setup(Context ctx) {
		
		HandlerThread handlerThread=new HandlerThread(WIFI_THREAD);
		handlerThread.start();
		Looper looper = handlerThread.getLooper();
		Handler handler = new Handler(looper,this);
		final IntentFilter filters = new IntentFilter();
		filters.addAction("android.net.wifi.WIFI_STATE_CHANGED");
//		filters.addAction("android.net.wifi.STATE_CHANGE");
		mReceiver = new BroadcastReceiver(){

			@Override
			public void onReceive(Context context, Intent intent) {
				Log.v(Constants.MESSAGE_TAG, "Recieve wifi change " + mEnableWifi);
				WifiManager wifi = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
				boolean isWifiEnabled = wifi.isWifiEnabled();
				if(isWifiEnabled = mEnableWifi && null != mBehaviors){
					Log.v(Constants.MESSAGE_TAG, "Change auto sync");
					for(IBehavior behavior:mBehaviors){
						behavior.execute(context);
					}
				}
			}
		};
		ctx.registerReceiver(mReceiver, filters, null, handler);
	}

	@Override
	public void cancel(Context ctx) {
		ctx.unregisterReceiver(mReceiver);
	}


	@Override
	public boolean handleMessage(Message msg) {
		// TODO Auto-generated method stub
		return false;
	}

}
