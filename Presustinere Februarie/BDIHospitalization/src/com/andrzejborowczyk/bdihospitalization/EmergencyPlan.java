package com.andrzejborowczyk.bdihospitalization;

import java.util.Date;

import jadex.runtime.IInternalEvent;
import jadex.runtime.IMessageEvent;
import jadex.runtime.Plan;


/**
 * Plan for acting in case of emergency. Notifies the personnel.
 * @author andrzej
 *
 */
@SuppressWarnings("serial")
public class EmergencyPlan extends Plan {

	public EmergencyPlan() {
		log("Created: " + this);
	}
	
	@Override
	public void body() {
		
		
		IMessageEvent me = (IMessageEvent)getInitialEvent();	
		log("Received: " + me.toString());
		
		String heartBeat = me.getContent().toString();	
		
		IInternalEvent event = createInternalEvent("gui_update");
		event.getParameter("content").setValue(new String[]{new Date().toString() ,heartBeat,"Notifying personnel"});
		dispatchInternalEvent(event);

	}
	private void log(String s) {
		System.out.println(getScope().getAgentName() + ": " + s);
	}
}
