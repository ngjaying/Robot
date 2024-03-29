package com.gmail.ngjaying.robot.model.receivers;

import java.util.ArrayList;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.gmail.najaying.robot.utils.Constants;
import com.gmail.ngjaying.robot.model.IBehavior;

public class AlarmReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		Log.v(Constants.MESSAGE_TAG, "Alarm Receiver is launched" );

		ArrayList<IBehavior> behaviors = (ArrayList<IBehavior>) intent.getExtras().getSerializable(Constants.ALARMCONDITION_EXTRA);

		for(IBehavior behavior:behaviors){
			behavior.execute(context);
		}
	}

}
