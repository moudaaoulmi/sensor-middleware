package com.andrzejborowczyk.bdihospitalization;

import java.util.Date;

import jadex.adapter.fipa.AgentDescription;
import jadex.adapter.fipa.AgentIdentifier;
import jadex.runtime.IGoal;
import jadex.runtime.IInternalEvent;
import jadex.runtime.IMessageEvent;
import jadex.runtime.Plan;


/**
 * Plan for reading the patient's temperature desire and sending it to the AC agent
 * @author andrzej
 *
 */
@SuppressWarnings("serial")
public class TemperatureDesirePlan extends Plan {

	public TemperatureDesirePlan() {
		log("Created: " + this);
	}

	@Override
	public void body() {
		// TODO Auto-generated method stub

		int desiredTemperature = ((Integer) (getBeliefbase().getBelief(
				"target_temperature").getFact())).intValue();

		IInternalEvent event = createInternalEvent("gui_update");
		event.getParameter("content").setValue(
				new String[] {new Date().toString(),  desiredTemperature + "", "inform AC agent" });
		dispatchInternalEvent(event);

		AgentIdentifier airConditioning = (AgentIdentifier) getBeliefbase()
				.getBelief("airconditioning").getFact();
		if (airConditioning == null) {
			IGoal df_search = createGoal("df_search");
			df_search.getParameter("description").setValue(
					getPropertybase().getProperty("service_airconditioning"));
			dispatchSubgoalAndWait(df_search);
			AgentDescription[] result = (AgentDescription[]) df_search
					.getParameterSet("result").getValues();
			if (result.length == 0) {
				fail();
			}
			airConditioning = result[0].getName();

			getBeliefbase().getBelief("airconditioning").setFact(
					airConditioning);
		}

		IMessageEvent outcoming = createMessageEvent("inform_temperature_desire");
		outcoming.setContent(desiredTemperature+"");

		outcoming.getParameterSet(jadex.adapter.fipa.SFipa.RECEIVERS).addValue(
				airConditioning);

		sendMessage(outcoming);

	}

	private void log(String s) {
		System.out.println(getScope().getAgentName() + ": " + s);
	}

}
