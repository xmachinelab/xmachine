package com.blacklight.xmachine.fundamentals.impl;

public class XANYEvent extends XEventImpl {
	
	public static XANYEvent SINGLETON = new XANYEvent();
	
	private XANYEvent() {
		super("*");
	}

}
