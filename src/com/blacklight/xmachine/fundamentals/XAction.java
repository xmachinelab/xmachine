package com.blacklight.xmachine.fundamentals;

public interface XAction extends XMember {

	final static char ACTION_MEMBER_LETTER = 'A';
	
	public String getActionName();
	public Object operation();
}
