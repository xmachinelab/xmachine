package com.blacklight.xmachine.fundamentals;

import java.util.List;

public interface XTransition extends XMember {

	final static char TRANSITION_MEMBER_LETTER = 'T';
	
	public long getFromStateIndex();
	public long getToStateIndex();
	public long getEventIndex();
	public void setEventIndex(long eventIndex);
	public long getConditionIndex();
	public List<Long> getActions();
}
