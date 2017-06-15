package com.blacklight.xmachine.fundamentals.impl;

import com.blacklight.xmachine.fundamentals.XEvent;

public class XEventImpl extends XAbstractMember implements XEvent {

	public XEventImpl(String eventName) {
		super(XEvent.EVENT_MEMBER_LETTER);
		setEventName(eventName);
	}

	private String eventName;

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	@Override
	public String toString() {
		return "EventImpl : "+ getXMachineIndex() +"[eventName=" + eventName + "]";
	}

	
}
