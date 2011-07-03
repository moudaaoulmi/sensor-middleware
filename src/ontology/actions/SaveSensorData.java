package ontology.actions;

import jade.content.AgentAction;
import ontology.concepts.AggregatedData;

public class SaveSensorData implements AgentAction
{
	protected AggregatedData op;
	
	public AggregatedData getOp() 
	{
		return op;
	}

	public void setOp(AggregatedData op) 
	{
		this.op = op;
	}

	public SaveSensorData( )
	{
	}
	
	
}
