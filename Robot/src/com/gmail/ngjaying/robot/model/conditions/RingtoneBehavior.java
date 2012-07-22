package com.gmail.ngjaying.robot.model.conditions;

import android.content.Context;
import android.media.AudioManager;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import com.gmail.najaying.robot.utils.Constants;
import com.gmail.ngjaying.robot.model.AbstractBehavior;

public class RingtoneBehavior extends AbstractBehavior {
	
	private int mState;
	public RingtoneBehavior(int state){
		mState = state;
	}
	
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(mState);
	}
	
	@Override
	public void execute(Context ctx) {
		Log.v(Constants.MESSAGE_TAG, "Vibrate behavior triggered");
		AudioManager audioManager = (AudioManager) ctx
				.getSystemService(Context.AUDIO_SERVICE);
		audioManager.setRingerMode(mState);
	}

	public static final Parcelable.Creator<RingtoneBehavior> CREATOR = new Parcelable.Creator<RingtoneBehavior>() {
		public RingtoneBehavior createFromParcel(Parcel in) {
			return new RingtoneBehavior(in);
		}

		public RingtoneBehavior[] newArray(int size) {
			return new RingtoneBehavior[size];
		}
	};
	
	private RingtoneBehavior(Parcel in){
		mState = in.readInt();
	}

}
