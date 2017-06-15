package com.blacklight.xmachine.fundamentals.impl;

import javax.xml.bind.annotation.XmlRootElement;

import com.blacklight.expressionist.Compiler;
import com.blacklight.expressionist.Program;
import com.blacklight.expressionist.exp.Expression;
import com.blacklight.expressionist.exp.NumberValueExpression;
import com.blacklight.xmachine.fundamentals.XAction;

@XmlRootElement
public class XVariableActionImpl extends XAbstractMember implements XAction{

	public XVariableActionImpl(String actionName,String variable,  String expression) {
		super(XAction.ACTION_MEMBER_LETTER);
		this.actionName = actionName;
		this.variable = variable;
		this.expression = expression;
		
		compile();
	}

	private void compile() {
		
		var = (NumberValueExpression) Program.exprMap.get(variable);
		
		exp = new Compiler(expression).parse();
		Program.exprStringMap.put(actionName, expression);
		Program.exprMap.put(actionName, exp);

	}

	private String actionName;
	private String variable;
	private String expression;
	private NumberValueExpression var;
	private Expression exp;
		
	
	public String getActionName() {
		return actionName;
	}
	
	public Object operation() {
		System.out.println(actionName + " : >>>>> OLD VAR VAL : " + var.fValue());
		float fValue = exp.fValue();
		var.setFvalue(fValue);
		System.out.println(actionName + " : (" +expression+ ") >>>>> OUT : " + fValue);
		System.out.println(actionName + " : >>>>> NEW VAR VAL : " + var.fValue());
		return fValue;
	}

}
