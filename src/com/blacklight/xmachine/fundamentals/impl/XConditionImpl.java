package com.blacklight.xmachine.fundamentals.impl;

import javax.xml.bind.annotation.XmlRootElement;

import com.blacklight.xmachine.fundamentals.XCondition;

@XmlRootElement
public class XConditionImpl extends XAbstractMember implements XCondition {

	String expression = "21/3==7";

	public XConditionImpl() {
		super(XCondition.CONDITION_MEMBER_LETTER);
	}
	
	public boolean validate() {
		return true;
	}

	@Override
	public String toString() {
		return "[Expression: " + expression +"]";
	}
}
