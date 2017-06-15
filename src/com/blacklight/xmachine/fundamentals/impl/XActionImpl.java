package com.blacklight.xmachine.fundamentals.impl;

import javax.xml.bind.annotation.XmlRootElement;

import com.blacklight.xmachine.fundamentals.XAction;

@XmlRootElement
public class XActionImpl extends XAbstractMember implements XAction{

	public XActionImpl() {
		super(XAction.ACTION_MEMBER_LETTER);
	}

	private String actionName;

	
	public String getActionName() {
		return actionName;
	}
	
	public Object operation() {
		return null;
	}

}
