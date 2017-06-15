package com.blacklight.xmachine.fundamentals;

public interface XMember extends XObject{

	char getMemberLetter();
	long getBase();
	long getProgramMemberIndex();
	long getXMachineIndex();
	void setProgramMemberIndex(long index);
	void setXMachineIndex(long index);
	void setProgramIndex(long programIndex);
	long getProgramIndex();
}
