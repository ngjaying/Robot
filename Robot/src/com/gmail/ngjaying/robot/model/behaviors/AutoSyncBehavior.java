package com.gmail.ngjaying.robot.model.behaviors;

import android.content.ContentResolver;
import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import com.gmail.najaying.robot.utils.Constants;
import com.gmail.ngjaying.robot.model.AbstractBehavior;

public class AutoSyncBehavior extends AbstractBehavior {

	private boolean mEnableSync;
	
	
	public AutoSyncBehavior(boolean enableSync) {
		super();
		this.mEnableSync = enableSync;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeByte((byte) (mEnableSync? 1 : 0));
	}
	
	@Override
	public void execute(Context ctx) {
		Log.v(Constants.MESSAGE_TAG, "Auto sync behavior triggered");
		if(!ContentResolver.getMasterSyncAutomatically())
    		ContentResolver.setMasterSyncAutomatically(mEnableSync);
	}

	public static final Parcelable.Creator<AutoSyncBehavior> CREATOR = new Parcelable.Creator<AutoSyncBehavior>() {
		public AutoSyncBehavior createFromParcel(Parcel in) {
			return new AutoSyncBehavior(in);
		}

		public AutoSyncBehavior[] newArray(int size) {
			return new AutoSyncBehavior[size];
		}
	};
	
	private AutoSyncBehavior(Parcel in){
		mEnableSync = in.readByte() == 1;
	}

}
