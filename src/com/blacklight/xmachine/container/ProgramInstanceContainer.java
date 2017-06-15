package com.blacklight.xmachine.container;

import java.util.concurrent.ConcurrentHashMap;

import com.blacklight.xmachine.instance.XProgramInstance;

public class ProgramInstanceContainer {
	
	ConcurrentHashMap<Long, XProgramInstance> programInstanceMap = null;

	public static ProgramInstanceContainer SINGLETON = new ProgramInstanceContainer();
	
	private ProgramInstanceContainer() {
		programInstanceMap = new ConcurrentHashMap<Long, XProgramInstance>() ;
	}
	
	public void addInstance(XProgramInstance instance) {
		programInstanceMap.put(instance.getXMachineIndex(), instance);
	}
	
	public XProgramInstance getProgramInstance(long instanceIndex) {
		return programInstanceMap.get(instanceIndex);
	}

}
