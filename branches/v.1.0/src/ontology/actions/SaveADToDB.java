package ontology.actions;

import jade.content.AgentAction;
import ontology.concepts.AggregatedData;

public class SaveADToDB implements AgentAction
{
	protected AggregatedData info;

	public AggregatedData getInfo()
	{
		return info;
	}

	public void setInfo(AggregatedData info)
	{
		this.info = info;
	}
	
}
