package com.blacklight.xmachine.container;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class StateActorIndexContainer {
	
	ConcurrentHashMap<Long, List<Long>> stateActorMap = null;

	public static StateActorIndexContainer SINGLETON = new StateActorIndexContainer();
	
	private StateActorIndexContainer() {
		stateActorMap = new ConcurrentHashMap<Long, List<Long>>();
	}
	
	public void addActorForState(long stateIndex, long actorIndex) {
		List<Long> actorIndexList = stateActorMap.get(stateIndex);
		if (actorIndexList == null){
			actorIndexList = new ArrayList<Long>();
			stateActorMap.put(stateIndex, actorIndexList);
			
		}
		actorIndexList.add(actorIndex);
	}
	
	public List<Long> getActorList(long stateIndex) {
		return stateActorMap.get(stateIndex);
	}

	public boolean isActorInState(long stateIndex, Long actorIndex) {
		List<Long> actorList = getActorList(stateIndex);
		return actorList == null ? false : actorList.contains(actorIndex);
	}

	public void removeFromState(long stateIndex, Long actorIndex) {
		getActorList(stateIndex).remove(actorIndex);
	}
}
