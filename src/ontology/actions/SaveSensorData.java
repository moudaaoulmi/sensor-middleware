package ontology.actions;

import jade.content.AgentAction;
import ontology.concepts.SensorData;

public class SaveSensorData implements AgentAction
{
	protected SensorData op;
	
	public SensorData getOp() 
	{
		return op;
	}

	public void setOp(SensorData op) 
	{
		this.op = op;
	}

	public SaveSensorData( )
	{
	}
	
	
}
