package com.andrzejborowczyk.bdihospitalization;

import java.util.Calendar;

/**
 * The class simulating patient's temperature desire.
 * 
 * @author maciej
 */
public class TemperatureDesire {

	// How old patient gets every heart beat
	private static final double GETTING_OLDER_STEP = +0.1;
    // Avarage temperature desired by patient
	private static final double TEMPERATURE_MEDIUM = 23;


	// Patient's desired temperature
	private int temperature;
	// Patient's range
	private int range;
	// Pressure factor changing over time
	private double temperatureChangeOverTime = 0;


	/**
	 * Construct a patient.
	 * @param range is initial range of a patient
	 */
	public TemperatureDesire(int range) {
		this.range = range;
		simulateDesiredTemperature();
	}

	public int getTemperature() {
		simulateDesiredTemperature(); // Line added to evaluate blood pressure dynamicly.
		return temperature;
	}

	public void simulateDesiredTemperature() {
		// Make pattient older
		range += GETTING_OLDER_STEP;
		// Simulate pressure in change in minute cycles
		temperatureChangeOverTime = Calendar.getInstance().get(Calendar.SECOND);

		// normalize to radian scale [-2 PI .. +2 PI]
		double x = 2
				* Math.PI
				* temperatureChangeOverTime
				/ (Calendar.getInstance().getActualMaximum(Calendar.SECOND) + 1);

		temperature = (int) (TEMPERATURE_MEDIUM + 0.5 * (Math.sin(x) ) * range);

	}

}
