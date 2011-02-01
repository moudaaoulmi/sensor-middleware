package com.andrzejborowczyk.bdihospitalization;

import java.util.Date;

import jadex.runtime.IExpression;
import jadex.runtime.IInternalEvent;
import jadex.runtime.IMessageEvent;
import jadex.runtime.Plan;

/**
 * Plan for fixing the heart beat.
 * Queryies belief base to find proper drug in order to fix the heart beat.
 * @author andrzej
 */
@SuppressWarnings("serial")
public class FixHeartBeatPlan extends Plan {

	public FixHeartBeatPlan() {
		log("Created: " + this);
	}
	
	@Override
	public void body() {
		
		
		IMessageEvent me = (IMessageEvent)getInitialEvent();	
		
		String heartBeatIs = (String) me.getContent();
		
		IExpression queryword = getExpression("query_heart_beat_drugs");

		String actionTaken = (String)queryword.execute("$heart_beat",heartBeatIs);

		IInternalEvent event = createInternalEvent("gui_update");
		event.getParameter("content").setValue(new String[]{new Date().toString(), heartBeatIs,actionTaken});
		dispatchInternalEvent(event);

	}
	private void log(String s) {
		System.out.println(getScope().getAgentName() + ": " + s);
	}
}
