package com.andrzejborowczyk.bdihospitalization;

import jadex.runtime.IMessageEvent;
import jadex.runtime.Plan;

/**
 * Plan for setting the desired temperature
 * @author andrzej
 *
 */
@SuppressWarnings("serial")
public class ACSetTemperature extends Plan {

	/**
	 * Constructor.
	 */
	public ACSetTemperature() {
		log("Created: " + this);				
	}


	/**
	 * @see jadex.runtime.Plan#body()
	 * 
	 * Body of plan for setting the desired temperature.
	 * Sets the beliefs of target, high and low temperature
	 */
	@Override
	public void body() {
		
		IMessageEvent me = (IMessageEvent)getInitialEvent();
		
		String desiredTemperature = (String) me.getContent();
		int targetTemperature = (new Integer(desiredTemperature)).intValue();
		
		getBeliefbase().getBelief("target_temperature").setFact(targetTemperature);
		getBeliefbase().getBelief("temperature_high").setFact(targetTemperature+2);
		getBeliefbase().getBelief("temperature_low").setFact(targetTemperature-2);

	}
	
	/**
	 * @param s
	 */
	private void log(String s) {
		System.out.println(getScope().getAgentName() + ": " + s);
	}
}
