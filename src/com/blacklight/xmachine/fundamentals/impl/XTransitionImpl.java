package com.blacklight.xmachine.fundamentals.impl;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.blacklight.xmachine.container.EventIndexContainer;
import com.blacklight.xmachine.container.IndexContainer;
import com.blacklight.xmachine.container.IndexContainer.IndexEnum;
import com.blacklight.xmachine.fundamentals.XCondition;
import com.blacklight.xmachine.fundamentals.XEvent;
import com.blacklight.xmachine.fundamentals.XState;
import com.blacklight.xmachine.fundamentals.XTransition;

@XmlRootElement
public class XTransitionImpl extends XAbstractMember implements XTransition {

	long conditionIndex;
	List<Long> actions;
	
	long fromStateIndex;
	long toStateIndex;
	
	long eventIndex = -1;
	
	public XTransitionImpl() {
		super(XTransition.TRANSITION_MEMBER_LETTER);
		
	}
	
	public XTransitionImpl(long fromIndex, long toIndex) {
		this();
		this.fromStateIndex = fromIndex;
		this.toStateIndex = toIndex;
		actions = new ArrayList<Long>();
	}

	@Override
	public long getFromStateIndex() {
		return fromStateIndex;
	}

	@Override
	public long getToStateIndex() {
		return toStateIndex;
	}

	public long getEventIndex() {
		return eventIndex;
	}

	public void setEventIndex(long eventIndex) {
		this.eventIndex = eventIndex;
		EventIndexContainer.SINGLETON.addTransitionForEvent(eventIndex, getXMachineIndex());
	}

	@Override
	public long getConditionIndex() {
		return conditionIndex;
	}

	@Override
	public List<Long> getActions() {
		return actions;
	}

	@Override
	public String toString() {
		
		XState fromState = (XState) IndexContainer.SINGLETON.getXObject(IndexEnum.OBJECT_INDEX,fromStateIndex);
		XState toState = (XState) IndexContainer.SINGLETON.getXObject(IndexEnum.OBJECT_INDEX,toStateIndex);
		XCondition condition = (XCondition) IndexContainer.SINGLETON.getXObject(IndexEnum.OBJECT_INDEX,conditionIndex);
		XEvent event = (XEvent) IndexContainer.SINGLETON.getXObject(IndexEnum.OBJECT_INDEX,eventIndex);
		
		return "Transition : [XMachineProgramIndex= "+getXMachineIndex()+"]\n"
				+ "		HANDLES [event : ("+event.getEventName()+") "+eventIndex+"] \n "
				+ "		[if(" + condition + ") \n "
				+ "			do {" + actions + "}\n "
				+ "		WHILE \n"
				+ "			fromState=(" + fromState.getStateName()+ ","+fromState.getXMachineIndex()+")==>  toState=(" + toState.getStateName()+ ","+toState.getXMachineIndex()+")" + "]";
	}
	
	
}
