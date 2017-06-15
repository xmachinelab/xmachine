package com.blacklight.xmachine.fundamentals.impl;

import com.blacklight.xmachine.container.IndexContainer;
import com.blacklight.xmachine.container.IndexContainer.IndexEnum;
import com.blacklight.xmachine.fundamentals.XMember;

public class XAbstractMember implements XMember {

	char memberLetter;
	long base=-1;
	long programMemberIndex=-1;
	long xmachineIndex=-1;
	
	long programIndex;

	public XAbstractMember() {
	}
	
	public XAbstractMember(char memberLetter) {
		this.memberLetter = memberLetter;
		base = getMemberLetter() * 10000;
	}
	
	@Override
	public char getMemberLetter() {
		return memberLetter;
	}

	@Override
	public long getBase() {
		return base;
	}

	@Override
	public long getProgramMemberIndex() {
		
		return programMemberIndex;
	}

	@Override
	public long getXMachineIndex() {

		return xmachineIndex;
	}

	@Override
	public void setProgramMemberIndex(long index) {
		this.programMemberIndex = index;
	}

	@Override
	public void setXMachineIndex(long index) {
		this.xmachineIndex = index;
		IndexContainer.SINGLETON.addIndex(IndexEnum.OBJECT_INDEX, this);
	}

	public long getProgramIndex() {
		return programIndex;
	}

	public void setProgramIndex(long programIndex) {
		this.programIndex = programIndex;
	}
}