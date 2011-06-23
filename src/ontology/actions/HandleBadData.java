package ontology.actions;

import jade.content.AgentAction;
import ontology.concepts.sensors.Sensor;

public class HandleBadData implements AgentAction
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
