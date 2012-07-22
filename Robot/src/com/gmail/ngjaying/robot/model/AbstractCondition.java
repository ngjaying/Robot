package com.gmail.ngjaying.robot.model;

import java.util.ArrayList;


public abstract class AbstractCondition implements ICondition {

	protected ArrayList<IBehavior> mBehaviors;

	public AbstractCondition() {
		super();
	}

	@Override
	public ArrayList<IBehavior> getBehaviors() {
		return mBehaviors;
	}

	@Override
	public void addBehavior(IBehavior behavior) {
		if(null == mBehaviors) mBehaviors = new ArrayList<IBehavior>();
		mBehaviors.add(behavior);
	}
	
}