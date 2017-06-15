package com.blacklight.xmachine.fundamentals.impl;

import javax.xml.bind.annotation.XmlRootElement;

import com.blacklight.expressionist.Compiler;
import com.blacklight.expressionist.Program;
import com.blacklight.expressionist.exp.Expression;
import com.blacklight.xmachine.fundamentals.XAction;

@XmlRootElement
public class XAssignmentActionImpl extends XAbstractMember implements XAction{

	public XAssignmentActionImpl(String actionName, String expression) {
		super(XAction.ACTION_MEMBER_LETTER);
		this.actionName = actionName;
		this.expression = expression;
		
		compile();
	}

	private void compile() {
		
		exp = new Compiler(expression).parse();
		Program.exprStringMap.put(actionName, expression);
		Program.exprMap.put(actionName, exp);

	}

	private String actionName;
	private String expression;
	private Expression exp;
	
	
	public String getActionName() {
		return actionName;
	}
	
	public Object operation() {
		float fValue = exp.fValue();
		System.out.println(actionName + " : (" +expression+ ") >>>>> OUT : " + fValue);
		return fValue;
	}

}
