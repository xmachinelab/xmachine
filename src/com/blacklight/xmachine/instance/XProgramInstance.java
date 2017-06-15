package com.blacklight.xmachine.instance;

import java.util.List;

import com.blacklight.xmachine.container.ActorIndexContainer;
import com.blacklight.xmachine.container.IndexContainer;
import com.blacklight.xmachine.container.ProgramInstanceContainer;
import com.blacklight.xmachine.container.StateActorIndexContainer;
import com.blacklight.xmachine.container.IndexContainer.IndexEnum;
import com.blacklight.xmachine.fundamentals.XAction;
import com.blacklight.xmachine.fundamentals.XCondition;
import com.blacklight.xmachine.fundamentals.XEvent;
import com.blacklight.xmachine.fundamentals.XObject;
import com.blacklight.xmachine.fundamentals.XProgram;
import com.blacklight.xmachine.fundamentals.XState;
import com.blacklight.xmachine.fundamentals.XTransition;
import com.blacklight.xmachine.fundamentals.impl.XANYEvent;
import com.blacklight.xmachine.index.ProgramInstanceIndex;

public class XProgramInstance implements XObject {

	XProgram program;
	private long machineIndex;
	
	public XProgramInstance(XProgram program) {
		this.program = program;
		machineIndex = ProgramInstanceIndex.nextValue();
		ProgramInstanceContainer.SINGLETON.addInstance(this);
	}
	
	public void doActionsForState(Long stateIndex) {
		XState state = (XState) IndexContainer.SINGLETON.getXObject(IndexEnum.OBJECT_INDEX,stateIndex);
		List<Long> actions = state.getActions();
		for (Long actıonIndex : actions) {
			XAction action = (XAction) IndexContainer.SINGLETON.getXObject(IndexEnum.OBJECT_INDEX,actıonIndex);
			action.operation();
		}
	}
	
	public long doTransitionsForState(Long stateIndex, XActor actor, XEventInstance event) {
		List<Long> transitions = program.getMembers(XTransition.TRANSITION_MEMBER_LETTER);
		for (Long tIndex : transitions) {
			XTransition transition = (XTransition) IndexContainer.SINGLETON.getXObject(IndexEnum.OBJECT_INDEX,tIndex);
			XEvent xEvent = (XEvent) IndexContainer.SINGLETON.getXObject(IndexEnum.OBJECT_INDEX,transition.getEventIndex());
			if (transition.getFromStateIndex() == stateIndex 
					&& (event.getEventName().equals(xEvent.getEventName()) 
							|| XANYEvent.SINGLETON.getXMachineIndex() == xEvent.getXMachineIndex() )) {
				if(transition(transition, actor)) {
					stateIndex = transition.getToStateIndex();
					break;
				}
			}
		}
		
		return stateIndex;
	}
	
	public boolean transition(XTransition transition, XActor actor) {
		
		XCondition condition = (XCondition) IndexContainer.SINGLETON.getXObject(IndexEnum.OBJECT_INDEX,transition.getConditionIndex());
		
		boolean result = false;
		
		if (condition == null || (result = condition.validate())) {
			
			System.out.println(">>> VALIDATED " + condition);
			System.out.println(">>> TRANSITION OCCURING " + condition);
			
			List<Long> actions = transition.getActions();
			
			for (long actionIndex : actions) {
				XAction xAction = (XAction) IndexContainer.SINGLETON.getXObject(IndexEnum.OBJECT_INDEX,actionIndex);
				xAction.operation();
			}
			
			updateActorState(actor, transition.getFromStateIndex(), transition.getToStateIndex());
			
			result = true;
		}
		
		return result;
	}

	private void updateActorState(XActor actor, long fromStateIndex, long toStateIndex) {

		XState xstate = (XState) IndexContainer.SINGLETON.getXObject(IndexEnum.OBJECT_INDEX,fromStateIndex);
		System.out.println(">>>>>> FROM STATE UPDATE FOR ACTOR : " + actor + ", FROM: state : " + xstate);
		
		if (!xstate.isStart()) {
			StateActorIndexContainer.SINGLETON.removeFromState(fromStateIndex, actor.getActorIndex());
		}

		StateActorIndexContainer.SINGLETON.addActorForState(toStateIndex, actor.getActorIndex());
		ActorIndexContainer.SINGLETON.addProgram(actor.getActorIndex(), getProgram().getXMachineIndex());
		
		xstate = (XState) IndexContainer.SINGLETON.getXObject(IndexEnum.OBJECT_INDEX,toStateIndex);
		System.out.println(">>>>>> STATE UPDATE FOR ACTOR : " + actor + ", TO: state : " + xstate);
		
		if (xstate.isFinal()) {
			//remove actor from everywhere
			StateActorIndexContainer.SINGLETON.removeFromState(toStateIndex, actor.getActorIndex());
			ActorIndexContainer.SINGLETON.removeProgram(actor.getActorIndex(), getProgram().getXMachineIndex());

		}
		
		doActionsForState(toStateIndex);
		
	}
	
	public XProgram getProgram() {
		return program;
	}

	@Override
	public long getXMachineIndex() {
		return machineIndex;
	}
	
}
