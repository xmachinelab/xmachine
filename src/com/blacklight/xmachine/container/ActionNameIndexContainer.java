package com.blacklight.xmachine.container;

import java.util.concurrent.ConcurrentHashMap;

import com.blacklight.xmachine.fundamentals.XAction;

public class ActionNameIndexContainer {
	
	ConcurrentHashMap<String, Long> actionNameIndexMap = null;

	public static ActionNameIndexContainer SINGLETON = new ActionNameIndexContainer();
	
	private ActionNameIndexContainer() {
		actionNameIndexMap = new ConcurrentHashMap<String, Long>() ;
	}
	
	public void addAction(XAction action) {
		actionNameIndexMap.put(action.getActionName(), action.getXMachineIndex());
	}
	
	public long getProgramInstance(String actionName) {
		return actionNameIndexMap.get(actionName);
	}

}
