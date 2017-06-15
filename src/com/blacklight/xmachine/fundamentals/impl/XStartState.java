package com.blacklight.xmachine.fundamentals.impl;

public class XStartState extends XStateImpl {

	public XStartState(String stateName) {
		super(stateName);
	}

	@Override
	public boolean isStart() {
		return true;
	}

}
