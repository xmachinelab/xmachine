package com.blacklight.xmachine.container;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import com.blacklight.xmachine.instance.XActor;

public class ActorIndexContainer {
	
	ConcurrentHashMap<String, Long> actorIdMap = null;
	ConcurrentHashMap<Long, XActor> actorIndexMap = null;
	ConcurrentHashMap<Long, Set<Long>> actorProgramIndexMap = null;

	public static ActorIndexContainer SINGLETON = new ActorIndexContainer();
	
	private ActorIndexContainer() {
		actorIdMap = new ConcurrentHashMap<String, Long>() ;
		actorIndexMap = new ConcurrentHashMap<Long, XActor> ();
		actorProgramIndexMap = new ConcurrentHashMap<Long, Set<Long>> ();
	}
	
	public void addActor(String actorId, XActor actor) {
		actorIdMap.put(actorId, actor.getActorIndex());
		actorIndexMap.put(actor.getActorIndex(), actor);
		actorProgramIndexMap.put(actor.getActorIndex(), new LinkedHashSet<Long>());
	}
	
	public void addProgram(long actorIndex, long programIndex) {
		Set<Long> programList = actorProgramIndexMap.get(actorIndex);
		if(programList == null) {
			programList = new LinkedHashSet<Long>();
			actorProgramIndexMap.put(actorIndex, programList);
		}
		
		programList.add(programIndex);
		
	}
	
	public Set<Long> getPrograms(long actorIndex) {
		return actorProgramIndexMap.get(actorIndex);
	}
	
	public XActor getActor(long actorIndex) {
		return actorIndexMap.get(actorIndex);
	}
	
	public XActor getActor(String actorId) {
		Long index = actorIdMap.get(actorId);
		XActor actor = null;

		if (index == null) {
			actor = new XActor(actorId);
			addActor(actorId, actor);
		} else {
			actor = getActor(index);
		}
		return actor ;
	}
	
	public boolean isActorInProgram(long actorIndex, long programIndex) {
		
		boolean result = false;
		
		Set<Long> programs = getPrograms(actorIndex);
		if (programs != null) {
			result = programs.contains(programIndex);
		}
		
		return result;
	}

	public void removeProgram(Long actorIndex, long programIndex) {
		Set<Long> programs = getPrograms(actorIndex);
		programs.remove(programIndex);
	}
}
