package ontology.concepts;

import utils.DateUtil;
import jade.content.Concept;

public class AggregatedData implements Concept 
{
	protected String info;
	
	public String getInfo()
	{
		return info;
	}

	public void setInfo(String info)
	{
		this.info = info;
	}

	public String toSQLInsert()
	{
		String sql = "INSERT INTO scenarios (`scenario`, `date`) VALUES " +
		"('"+info+"', NOW())";

		return sql;
	}
	
	public AggregatedData()
	{
		
	}
}
