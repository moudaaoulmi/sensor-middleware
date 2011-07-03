package ontology.actions;

import ontology.concepts.AggregatedData;
import ontology.concepts.sensors.Sensor;
import jade.content.AgentAction;

public class StoreAggregatedData implements AgentAction
{
	protected AggregatedData ad;

	public AggregatedData getSensor()
	{
		return ad;
	}

	public void setSensor(AggregatedData sensor)
	{
		this.ad = sensor;
	}
}
