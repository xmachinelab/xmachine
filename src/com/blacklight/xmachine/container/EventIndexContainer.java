package com.blacklight.xmachine.container;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import com.blacklight.xmachine.container.IndexContainer.IndexEnum;
import com.blacklight.xmachine.fundamentals.XEvent;
import com.blacklight.xmachine.fundamentals.XProgram;
import com.blacklight.xmachine.fundamentals.XTransition;
import com.blacklight.xmachine.fundamentals.impl.XANYEvent;

public class EventIndexContainer {
	
	ConcurrentHashMap<String, List<Long>> eventNameMap = null;
	ConcurrentHashMap<Long, List<Long>> eventTransitionsMap = null;
	ConcurrentHashMap<String, Set<Long>> eventProgramsMap = null;

	public static EventIndexContainer SINGLETON = new EventIndexContainer();
	
	private EventIndexContainer() {
		eventNameMap = new ConcurrentHashMap<String, List<Long>>();
		eventTransitionsMap = new ConcurrentHashMap<Long, List<Long>>();
		eventProgramsMap = new ConcurrentHashMap<String, Set<Long>>();
	}
	
	public void addEventForEventName(String eventName, Long eventIndex) {
		List<Long> eventList = eventNameMap.get(eventName);
		if (eventList == null){
			eventList = new ArrayList<Long>();
			if (!eventName.equals("*"))
				eventList.add(XANYEvent.SINGLETON.getXMachineIndex());
			eventNameMap.put(eventName, eventList);
			
		}
		eventList.add(eventIndex);
	}
	
	public void addTransitionForEvent(long eventIndex, long transitionIndex) {
		List<Long> transitions = eventTransitionsMap.get(eventIndex);
		if (transitions == null) {
			transitions = new ArrayList<Long>();
			eventTransitionsMap.put(eventIndex, transitions);
		}
		
		XTransition transition = (XTransition) IndexContainer.SINGLETON.getXObject(IndexEnum.OBJECT_INDEX, transitionIndex);
		XEvent event = (XEvent) IndexContainer.SINGLETON.getXObject(IndexEnum.OBJECT_INDEX, eventIndex);

		addProgramForEvent(event.getEventName(), transition.getProgramIndex());
		
		transitions.add(transitionIndex);
	}
	
	private void addProgramForEvent(String eventName, long programIndex) {
		Set<Long> programs = eventProgramsMap.get(eventName);
		if (programs == null) {
			programs = new LinkedHashSet<Long>();
			eventProgramsMap.put(eventName, programs);
		}
		
		programs.add(programIndex);
		
	}

	public List<Long> getEventList(String eventName) {
		return eventNameMap.get(eventName);
	}

	public List<Long> getTransitionList(long eventIndex) {
		return eventTransitionsMap.get(eventIndex);
	}

	public Set<Long> getProgramList(String eventName) {
		return eventProgramsMap.get(eventName);
	}

	public List<XTransition> getEventTransitionListForProgram(String eventName, long programIndex) {

		XProgram program = (XProgram) IndexContainer.SINGLETON.getXObject(IndexEnum.OBJECT_INDEX, programIndex);
		List<Long> transitions = program.getMembers(XTransition.TRANSITION_MEMBER_LETTER);
		List<XTransition> returnList = new ArrayList<XTransition>();
		
		for (Long transIndex : transitions) {
			XTransition trans = (XTransition) IndexContainer.SINGLETON.getXObject(IndexEnum.OBJECT_INDEX, transIndex);
			long eventIndex = trans.getEventIndex();
			XEvent event = (XEvent) IndexContainer.SINGLETON.getXObject(IndexEnum.OBJECT_INDEX, eventIndex);
			
			if (event.getEventName().equals(eventName) || event.getXMachineIndex() == XANYEvent.SINGLETON.getXMachineIndex()) {
				returnList.add(trans);
			}
		}
		
		return returnList;
	}

}
