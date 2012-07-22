package com.gmail.ngjaying.robot.model;

import java.util.ArrayList;

import android.content.Context;

public interface ICondition {
	public ArrayList<IBehavior> getBehaviors();
	public void addBehavior(IBehavior behavior);
	public void setup(Context ctx);
	public void cancel(Context ctx);
}
