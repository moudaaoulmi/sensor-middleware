package ontology.actions;

import jade.content.AgentAction;
import jade.content.Concept;
import ontology.concepts.sensors.Sensor;

public class StoreInterpretedData implements AgentAction
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
