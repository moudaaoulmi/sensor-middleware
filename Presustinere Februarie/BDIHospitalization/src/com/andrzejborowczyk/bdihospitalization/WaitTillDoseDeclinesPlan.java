package com.andrzejborowczyk.bdihospitalization;

import jadex.runtime.Plan;

@SuppressWarnings("serial")
public class WaitTillDoseDeclinesPlan extends Plan {

	public WaitTillDoseDeclinesPlan() {
		log("Created: " + this);
	}
	
	@Override
	public void body() {
		int dose = ((Integer)getBeliefbase().getBelief("current_dose").getFact()).intValue();
		
		int dose_target =((Integer)getBeliefbase().getBelief("dose_target").getFact()).intValue();
		

		
		
		while(dose > dose_target){
			waitFor(1000);
			dose = ((Integer) getBeliefbase().getBelief("current_dose")
					.getFact()).intValue();
			dose = Math.max( dose - 1, dose_target);
			getBeliefbase().getBelief("current_dose").setFact(
					new Integer(dose));
		}

	}

	private void log(String s) {
		System.out.println(getScope().getAgentName() + ": " + s);
	}
}
