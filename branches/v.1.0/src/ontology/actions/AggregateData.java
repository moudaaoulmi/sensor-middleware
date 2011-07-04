package ontology.actions;

import ontology.concepts.sensors.Sensor;
import jade.content.AgentAction;

public class AggregateData implements AgentAction
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