package com.andrzejborowczyk.bdihospitalization;

import java.util.Calendar;

/**
 * The class simulating patient's state.
 * 
 * @author maciej
 */
public class Patient {

	// How old patient gets every heart beat
	private static final double GETTING_OLDER_STEP = +0.1;
    // Avarage pressure of a patient
	private static final double PRESSURE_MEDIUM = 80.0;

	// Patient's blood pressure
	private int pressure;
	// Patient's age
	private int age;
	// Pressure factor changing over time
	private double pressureChangeOverTime = 0;

	/**
	 * Construct a patient.
	 * @param age is initial age of a patient
	 */
	public Patient(int age) {
		this.age = age;
		heartBeat();
	}

	public int getPressure() {
		heartBeat(); // Line added to evaluate blood pressure dynamicly.
		return pressure;
	}

	public void heartBeat() {
		// Make pattient older
		age += GETTING_OLDER_STEP;
		// Simulate pressure in change in minute cycles
		pressureChangeOverTime = Calendar.getInstance().get(Calendar.SECOND);

		// normalize to radian scale [-2 PI .. +2 PI]
		double x = 2
				* Math.PI
				* pressureChangeOverTime
				/ (Calendar.getInstance().getActualMaximum(Calendar.SECOND) + 1);
		// normalize to [-1..+1] scale

		pressure = (int) (PRESSURE_MEDIUM + 0.5 * (Math.sin(x)) * age);

	}

}
