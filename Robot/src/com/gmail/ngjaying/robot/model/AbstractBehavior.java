package com.gmail.ngjaying.robot.model;

import android.os.Parcel;
import android.os.Parcelable;

public abstract class AbstractBehavior implements IBehavior, Parcelable {

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {

	}

}
