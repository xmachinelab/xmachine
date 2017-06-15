package com.blacklight.xmachine.fundamentals.impl;

public class XFinalState extends XStateImpl {

	public XFinalState(String stateName) {
		super(stateName);
	}

	@Override
	public boolean isFinal() {
		return true;
	}
}
