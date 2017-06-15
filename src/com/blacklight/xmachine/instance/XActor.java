package com.blacklight.xmachine.instance;

import com.blacklight.xmachine.index.ActorIndex;

public class XActor {
	
	long actorIndex;
	
	String actorId;
	
	public XActor(String actorId) {
		this.actorId = actorId;
		this.actorIndex = ActorIndex.nextValue();
		
	}

	public Long getActorIndex() {
		return actorIndex;
	}
	
	@Override
	public String toString() {
		return "XActor [actorIndex=" + actorIndex + ", actorId=" + actorId + "]";
	}

}
