package com.blacklight.xmachine;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import javax.xml.bind.JAXBException;

import com.blacklight.xmachine.container.ActorIndexContainer;
import com.blacklight.xmachine.container.EventIndexContainer;
import com.blacklight.xmachine.container.IndexContainer;
import com.blacklight.xmachine.container.IndexContainer.IndexEnum;
import com.blacklight.xmachine.container.StateActorIndexContainer;
import com.blacklight.xmachine.fundamentals.XAction;
import com.blacklight.xmachine.fundamentals.XEvent;
import com.blacklight.xmachine.fundamentals.XProgram;
import com.blacklight.xmachine.fundamentals.XState;
import com.blacklight.xmachine.fundamentals.XTransition;
import com.blacklight.xmachine.fundamentals.impl.XANYEvent;
import com.blacklight.xmachine.fundamentals.impl.XEventImpl;
import com.blacklight.xmachine.fundamentals.impl.XExpressionActionImpl;
import com.blacklight.xmachine.fundamentals.impl.XFinalState;
import com.blacklight.xmachine.fundamentals.impl.XProgramImpl;
import com.blacklight.xmachine.fundamentals.impl.XStartState;
import com.blacklight.xmachine.fundamentals.impl.XStateImpl;
import com.blacklight.xmachine.fundamentals.impl.XTransitionImpl;
import com.blacklight.xmachine.fundamentals.impl.XVariableActionImpl;
import com.blacklight.xmachine.instance.XActor;
import com.blacklight.xmachine.instance.XEventInstance;
import com.blacklight.xmachine.instance.XProgramInstance;

public class XMachineMain {

	public static void main(String[] args) throws JAXBException, IOException {
		xmachine();
		
		System.err.println();
		System.err.println();
		System.err.println();
		
		for(;;) {

			XEventInstance event = new XEventInstance("EVENT1","afsinb");
			
			Set<Long> programList = EventIndexContainer.SINGLETON.getProgramList(event.getEventName());
			XActor actor = ActorIndexContainer.SINGLETON.getActor(event.getActorId());
			
			for (Long programIndex : programList) {
				
				XProgram program = (XProgram) IndexContainer.SINGLETON.getXObject(IndexEnum.OBJECT_INDEX, programIndex);
				XProgramInstance instace = new XProgramInstance(program);
				
				if (ActorIndexContainer.SINGLETON.isActorInProgram(actor.getActorIndex(), programIndex)) {
					List<XTransition> transitions = EventIndexContainer.SINGLETON.getEventTransitionListForProgram(event.getEventName(), programIndex);
					for (XTransition transition : transitions) {
						long fromStateIndex = transition.getFromStateIndex();
						
						if (StateActorIndexContainer.SINGLETON.isActorInState(fromStateIndex, actor.getActorIndex())) {
							instace.transition(transition, actor);
							break;
						}
					}
				} else {
					instace.doTransitionsForState(program.getStartStateIndex(), actor, event);
					
				}
			}

//			XEventInstance event = new XEventInstance("EVENT1","afsinb");
//			
//			List<Long> eventList = EventIndexContainer.SINGLETON.getEventList(event.getEventName());
//			XActor actor = ActorIndexContainer.SINGLETON.getActor(event.getActorId());
//			
//			for (Long eventId : eventList) {
//				XEvent xEvent = (XEvent) IndexContainer.SINGLETON.getXObject(IndexEnum.OBJECT_INDEX, eventId);
//				List<Long> transitionList = EventIndexContainer.SINGLETON.getTransitionList(xEvent.getXMachineIndex());
//				
//				for (Long transIndex : transitionList) {
//					XTransition trans = (XTransition) IndexContainer.SINGLETON.getXObject(IndexEnum.OBJECT_INDEX, transIndex);
//					long fromStateIndex = trans.getFromStateIndex();
//					
//					XState state = (XState) IndexContainer.SINGLETON.getXObject(IndexEnum.OBJECT_INDEX, fromStateIndex);
//					if (state.isStart()  || StateActorIndexContainer.SINGLETON.isActorInState(fromStateIndex, actor.getActorIndex())) {
//						instace.transition(trans, actor);
//					}
//				}
//			}
			System.in.read();
		}
		

	}
	
	static XProgramImpl xmachine() {
		
		XProgramImpl prog = new XProgramImpl("ExMachina");
		
		XState ss = new XStartState("Start");
		prog.add(ss);
		
		XState s2 = new XStateImpl("S2");
		prog.add(s2);

		XState s3 = new XStateImpl("S3");
		prog.add(s3);

		XState s4 = new XStateImpl("S4");
		prog.add(s4);

		XState ff = new XFinalState("Final");
		prog.add(ff);
	
		XTransition t1 = new XTransitionImpl(ss.getXMachineIndex(),s2.getXMachineIndex());
		prog.add(t1);
		XEvent e1 = new XEventImpl("EVENT1");
		prog.add(e1);
		t1.setEventIndex(e1.getXMachineIndex());
		
		XTransition t2 = new XTransitionImpl(ss.getXMachineIndex(),s3.getXMachineIndex());
		prog.add(t2);
		t2.setEventIndex(XANYEvent.SINGLETON.getXMachineIndex());
		
		XTransition t3 = new XTransitionImpl(s2.getXMachineIndex(),s3.getXMachineIndex());
		prog.add(t3);
		t3.setEventIndex(e1.getXMachineIndex());

		XTransition t4 = new XTransitionImpl(s3.getXMachineIndex(),s4.getXMachineIndex());
		prog.add(t4);
		t4.setEventIndex(XANYEvent.SINGLETON.getXMachineIndex());
		
		XTransition t5 = new XTransitionImpl(s4.getXMachineIndex(),ff.getXMachineIndex());
		prog.add(t5);
		t5.setEventIndex(XANYEvent.SINGLETON.getXMachineIndex());

		XAction varAction = new XExpressionActionImpl("v1", "0");
		prog.add(varAction);
		s2.addAction(varAction.getXMachineIndex());

		
		XAction expAction = new XExpressionActionImpl("fx", "v1*3+5*7");
		prog.add(expAction);
		s2.addAction(expAction.getXMachineIndex());
		
		XAction assAction = new XVariableActionImpl("ass", "v1", "fx() +v1");
		prog.add(assAction);
		s2.addAction(assAction.getXMachineIndex());
		

		XAction callAction = new XExpressionActionImpl("c", "ass");
		prog.add(callAction);
		s2.addAction(callAction.getXMachineIndex());
		s2.addAction(callAction.getXMachineIndex());
		
		System.out.println(prog);
		
		return prog;
	}

}
