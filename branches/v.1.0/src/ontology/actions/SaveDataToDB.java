package ontology.actions;

import ontology.concepts.sensors.Sensor;
import jade.content.AgentAction;

public class SaveDataToDB implements AgentAction
{
	protected Sensor sql;

	public Sensor getSql()
	{
		return sql;
	}

	public void setSql(Sensor sql)
	{
		this.sql = sql;
	}
}
