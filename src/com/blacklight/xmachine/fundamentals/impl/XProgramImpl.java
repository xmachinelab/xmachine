package com.blacklight.xmachine.fundamentals.impl;

import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.blacklight.xmachine.container.ActionNameIndexContainer;
import com.blacklight.xmachine.container.EventIndexContainer;
import com.blacklight.xmachine.container.IndexContainer;
import com.blacklight.xmachine.container.IndexContainer.IndexEnum;
import com.blacklight.xmachine.fundamentals.XAction;
import com.blacklight.xmachine.fundamentals.XCondition;
import com.blacklight.xmachine.fundamentals.XEvent;
import com.blacklight.xmachine.fundamentals.XMember;
import com.blacklight.xmachine.fundamentals.XProgram;
import com.blacklight.xmachine.fundamentals.XState;
import com.blacklight.xmachine.fundamentals.XTransition;
import com.blacklight.xmachine.index.MemberIndex;
import com.blacklight.xmachine.index.ProgramIndex;

@XmlRootElement
public class XProgramImpl implements XProgram {

	String programName;

	List<Long> members;
	List<Long> states;
	List<Long> transitions;
	List<Long> conditions;
	List<Long> actions;
	List<Long> events;
	
	long startStateIndex;

	private MemberIndex memberSequence = null;

	private long programIndex = -1;

	private long programBase = 1000000;

	private long xMachineIndex;
	
	public XProgramImpl() {
		programIndex = ProgramIndex.nextValue();
		xMachineIndex = programIndex * programBase;

		IndexContainer.SINGLETON.addIndex(IndexEnum.OBJECT_INDEX, this);
		IndexContainer.SINGLETON.addIndex(IndexEnum.PROGRAM_INDEX, this);
		
		memberSequence = new MemberIndex();
		
		states = new LinkedList<Long>();
		transitions = new LinkedList<Long>();
		conditions = new LinkedList<Long>();
		actions = new LinkedList<Long>();
		members = new LinkedList<Long>();
		events = new LinkedList<Long>();

		add(XANYEvent.SINGLETON);
	}
	
	
	public XProgramImpl(String programName) {
		this();
		this.programName = programName;
	}
	

	private void setStartState(long startIndex) {
		this.startStateIndex = startIndex;
	}

	public long getStartStateIndex() {
		return startStateIndex;
	}
	
	@Override
	public void add(XMember member) {
		
		long programMemberIndex = getMemberSequence().nextValue(member);
		long xmachineMemberIndex = getXMachineIndex() + programMemberIndex;
		
		member.setProgramMemberIndex(programMemberIndex);
		member.setXMachineIndex(xmachineMemberIndex);
		member.setProgramIndex(getXMachineIndex());
		
		switch (member.getMemberLetter()) {
			case XAction.ACTION_MEMBER_LETTER:
				addAction(xmachineMemberIndex);
				ActionNameIndexContainer.SINGLETON.addAction((XAction) member);
				break;
			case XCondition.CONDITION_MEMBER_LETTER:
				addCondition(xmachineMemberIndex);
				break;
			case XState.STATE_MEMBER_LETTER:
				addState(xmachineMemberIndex);
				break;
			case XTransition.TRANSITION_MEMBER_LETTER:
				addTransition(xmachineMemberIndex);
				break;
			case XEvent.EVENT_MEMBER_LETTER:
				XEvent event = (XEvent) member;
				EventIndexContainer.SINGLETON.addEventForEventName(event.getEventName(), xmachineMemberIndex);
				addEvent(xmachineMemberIndex);
				break;
			default:
				break;
		}
		
		members.add(xmachineMemberIndex);
	}
	
	
	private void addState(long stateIndex) {
		states.add(stateIndex);
		XState state = (XState) IndexContainer.SINGLETON.getXObject(IndexEnum.OBJECT_INDEX,stateIndex);
		if (state.isStart()) {
			setStartState(stateIndex);
		}
	}

	private void addCondition(long conditionIndex) {
		conditions.add(conditionIndex);
	}
	
	private void addAction(long actionIndex) {
		actions.add(actionIndex);
	}

	private void addTransition(long transitionIndex) {
		transitions.add(transitionIndex);
	}

	private void addEvent(long eventIndex) {
		events.add(eventIndex);
	}

	private MemberIndex getMemberSequence() {
		return memberSequence;
	}

	@Override
	public long getProgramBase() {
		return programBase;
	}

	@Override
	public long getXMachineIndex() {
		return xMachineIndex;
	}

	public List<Long> getMembers(char memberLetter) {
		
		List<Long> resultMembers = null;
		
		switch (memberLetter) {
			case XAction.ACTION_MEMBER_LETTER:
				resultMembers = actions;
				break;
			case XCondition.CONDITION_MEMBER_LETTER:
				resultMembers = conditions;
				break;
			case XState.STATE_MEMBER_LETTER:
				resultMembers = states;
				break;
			case XTransition.TRANSITION_MEMBER_LETTER:
				resultMembers = transitions;
				break;
			default:
				break;
		}

		return resultMembers;
	}
	
	@Override
	public String toString() {
		return "XProgramImpl [programName=" + programName + ", transitions=" + transitions + ", startState="
				+ startStateIndex + ", xMachineProgramIndex=" + xMachineIndex + "]";
	}
	
}
