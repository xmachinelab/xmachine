package com.blacklight.xmachine.fundamentals.impl;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.blacklight.xmachine.fundamentals.XState;

@XmlRootElement
public class XStateImpl extends XAbstractMember implements XState {

	String stateName;
	List<Long> actions;
	
	public XStateImpl() {
		super(XState.STATE_MEMBER_LETTER);
		actions = new ArrayList<Long>();
	}
	
	public XStateImpl(String stateName) {
		this();
		this.stateName = stateName;
	}

	public String getStateName() {
		return stateName;
	}
	
	public void addAction(long actionIndex) {
		actions.add(actionIndex);
	}

	@Override
	public List<Long> getActions() {
		return actions;
	}

	@Override
	public boolean isStart() {
		return false;
	}

	@Override
	public boolean isFinal() {
		return false;
	}
	
	@Override
	public String toString() {
		return "[stateName=" + stateName + "]";
	}
}
