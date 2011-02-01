package com.andrzejborowczyk.bdihospitalization;

import java.util.Date;

import jadex.adapter.fipa.AgentDescription;
import jadex.adapter.fipa.AgentIdentifier;
import jadex.runtime.IGoal;
import jadex.runtime.IInternalEvent;
import jadex.runtime.IMessageEvent;
import jadex.runtime.Plan;

/**
 *plan for measuring the harbeat, setting the belief base and informin either injecion agent
 * injection agent and emergency agent 
 * @author andrzej
 *
 
 *
 */
@SuppressWarnings("serial")
public class HeartBeatMeasurePlan extends Plan {

	//kinds of doses
	private static final int NORMAL_DOSE = 1;
	private static final int SUPER_DOSE = 2;
	
	
	@Override
	public void body() {

		int heartBeat = ((Integer) (getBeliefbase().getBelief("heart_beat")
				.getFact())).intValue();
		int heartBeatHigh = ((Integer) (getBeliefbase().getBelief(
				"heart_beat_high").getFact())).intValue();
		int heartBeatMuchTooHigh = ((Integer) (getBeliefbase().getBelief(
				"heart_beat_much_too_high").getFact())).intValue();
		int heartBeatLow = ((Integer) (getBeliefbase().getBelief(
				"heart_beat_low").getFact())).intValue();
		int heartBeatMuchTooLow = ((Integer) (getBeliefbase().getBelief(
				"heart_beat_much_too_low").getFact())).intValue();

		IInternalEvent event = createInternalEvent("gui_update");

		if (heartBeat >= heartBeatLow && heartBeat <= heartBeatHigh) {
			event.getParameter("content").setValue(
					new String[] {new Date().toString(),  heartBeat + "", "doing nothing" });
			dispatchInternalEvent(event);
		} else {
			
			int dose = 0;
			
			event.getParameter("content").setValue(
					new String[] {new Date().toString(),  heartBeat + "",
							"informing the injection agent" });

			AgentIdentifier injection = (AgentIdentifier) getBeliefbase()
					.getBelief("injection").getFact();
			if (injection == null) {
				IGoal df_search = createGoal("df_search");
				df_search.getParameter("description").setValue(
						getPropertybase().getProperty("service_injection"));
				dispatchSubgoalAndWait(df_search);
				AgentDescription[] result = (AgentDescription[]) df_search
						.getParameterSet("result").getValues();
				if (result.length == 0) {
					fail();
				}
				injection = result[0].getName();

				getBeliefbase().getBelief("injection").setFact(injection);
			}

			AgentIdentifier emergency = (AgentIdentifier) getBeliefbase()
					.getBelief("emergency").getFact();
			if (emergency == null) {
				IGoal df_search = createGoal("df_search");
				df_search.getParameter("description").setValue(
						getPropertybase().getProperty("service_emergency"));
				dispatchSubgoalAndWait(df_search);
				AgentDescription[] result = (AgentDescription[]) df_search
						.getParameterSet("result").getValues();
				if (result.length == 0) {
					fail();
				}
				emergency = result[0].getName();

				getBeliefbase().getBelief("emergency").setFact(emergency);
			}

			IMessageEvent outcoming = createMessageEvent("inform_heart_beat");
			if (heartBeat > heartBeatMuchTooHigh) {
				dose = SUPER_DOSE;
				event
						.getParameter("content")
						.setValue(
								new String[] {new Date().toString(),  heartBeat + "",
										"informing the injection agent + emergency agent alert" });
				outcoming.getParameterSet(jadex.adapter.fipa.SFipa.RECEIVERS)
				.addValue(emergency);

				outcoming.setContent("much_too_high");
			} else if (heartBeat > heartBeatHigh) {
				dose = NORMAL_DOSE;
				outcoming.setContent("too_high");

			} else if (heartBeat < heartBeatMuchTooLow) {
				dose = SUPER_DOSE;
				event
						.getParameter("content")
						.setValue(
								new String[] {new Date().toString(),  heartBeat + "",
										"informing the injection agent + emergency agent alert" });
				outcoming.getParameterSet(jadex.adapter.fipa.SFipa.RECEIVERS)
				.addValue(emergency);
				outcoming.setContent("much_too_low");
			} else if (heartBeat < heartBeatLow) {
				dose = NORMAL_DOSE;
				outcoming.setContent("too_low");
			}
			
			//assert dose <> 0
			
			//apply dose change
			int old_dose = ((Integer)getBeliefbase().getBelief("current_dose").getFact()).intValue();
			int new_dose = old_dose + dose;
			getBeliefbase().getBelief("current_dose").setFact(new Integer(new_dose));
			
			outcoming.getParameterSet(jadex.adapter.fipa.SFipa.RECEIVERS)
					.addValue(injection);

			sendMessage(outcoming);

			dispatchInternalEvent(event);
		}
	}

	@SuppressWarnings("unused")
	private void log(String s) {
		System.out.println(getScope().getAgentName() + ": " + s);
	}

}
