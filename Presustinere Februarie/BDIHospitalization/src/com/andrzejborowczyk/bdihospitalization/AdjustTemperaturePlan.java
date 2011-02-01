package com.andrzejborowczyk.bdihospitalization;

import java.util.Date;

import jadex.runtime.IInternalEvent;
import jadex.runtime.Plan;

/**
  * Plan for adjust the temperature.
 * It either decreases or decreases the temperature up to the moment it reaches
 * target value.
 * @author user2
 */
public class AdjustTemperaturePlan extends Plan {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1474848921679694908L;

	public AdjustTemperaturePlan() {
		log("Created: " + this);
	}

	/**
	 * body of the adjust temperature plan
	 * adjusts the temperature in the room
	 */
	@Override
	public void body() {
		int temperature = ((Integer)(getBeliefbase().getBelief("room_temperature").getFact())).intValue();
	
		int target = ((Integer)(getBeliefbase().getBelief("target_temperature").getFact())).intValue();

		
		while (temperature > target){
			//decrease temperature			
			waitFor(1000);
			temperature -= 1;
			log("Temperature set to: " + temperature);
			IInternalEvent event = createInternalEvent("gui_update");
			event.getParameter("content").setValue(new String[]{new Date().toString() , +temperature+"",target+"","decreasing"});
			dispatchInternalEvent(event);
			getBeliefbase().getBelief("room_temperature").setFact(new Integer(temperature));
		}
		while (temperature < target){
			//increase temperature
			waitFor(1000);
			temperature += 1;
			log("Temperature set to: " + temperature);
			IInternalEvent event = createInternalEvent("gui_update");
			event.getParameter("content").setValue(new String[]{new Date().toString(), temperature+"",target+"","increasing"});
			dispatchInternalEvent(event);
			getBeliefbase().getBelief("room_temperature").setFact(new Integer(temperature));
		}
	
		log("Temperature is in proper range");
	}

	private void log(String s) {
		System.out.println(getScope().getAgentName() + ": " + s);
	}
}
