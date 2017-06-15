package com.blacklight.xmachine.fundamentals;

public interface XCondition extends XMember{

	final static char CONDITION_MEMBER_LETTER = 'C';

	public boolean validate();
}
