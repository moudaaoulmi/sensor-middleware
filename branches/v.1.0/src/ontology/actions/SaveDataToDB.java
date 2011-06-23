package ontology.actions;

import jade.content.AgentAction;

public class SaveDataToDB implements AgentAction
{
	protected String sql;

	public String getSql()
	{
		return sql;
	}

	public void setSql(String sql)
	{
		this.sql = sql;
	}
}
