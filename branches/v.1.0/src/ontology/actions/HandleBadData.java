package ontology.actions;

import ontology.concepts.SensorData;
import ontology.concepts.sensors.Sensor;
import jade.content.Concept;

public class HandleBadData implements Concept
{
	protected Sensor sensor;
	
	public Sensor getSensor()
	{
		return sensor;
	}

	public void setSensor(Sensor sensor)
	{
		this.sensor = sensor;
	}
}
