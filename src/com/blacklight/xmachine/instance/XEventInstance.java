package com.blacklight.xmachine.instance;

import java.util.ArrayList;
import java.util.List;

import com.blacklight.xmachine.fundamentals.XProgram;

public class XEventInstance {

	String eventName;
	
	String actorId;
	
	List<Long> programList;
	
	public XEventInstance(String eventName, String actorId) {
		this.eventName = eventName;
		this.actorId = actorId;
		programList = new ArrayList<Long>();
	}

	public String getEventName() {
		return eventName;
	}

	public String getActorId() {
		return actorId;
	}

	public void addProgram(XProgram program) {
		programList.add(program.getXMachineIndex());
	}
	
	public List<Long> getProgramList() {
		return programList;
	}

	public boolean isProgramExecuted(long programIndex) {
		return programList.contains(programIndex);
	}
	
}
