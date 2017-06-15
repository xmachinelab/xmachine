package com.blacklight.xmachine.fundamentals;

import java.util.List;

public interface XState extends XMember {

	final static char STATE_MEMBER_LETTER = 'S';

	public String getStateName();

	public void addAction(long actionIndex);

	public List<Long> getActions();

	public boolean isStart();

	public boolean isFinal();

} 
