package com.blacklight.xmachine.fundamentals;

import java.util.List;

public interface XProgram extends XObject {

	long getStartStateIndex();
	void add(XMember member);
	List<Long> getMembers(char meLmberLetter);
	long getProgramBase();
	long getXMachineIndex();
}
