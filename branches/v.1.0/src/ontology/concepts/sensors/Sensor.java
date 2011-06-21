package ontology.concepts.sensors;

import jade.content.Concept;


public abstract class Sensor implements ISensor, Concept
{
	protected int idSensor;
	protected int value;
	
	public int getValue()
	{
		return value;
	}

	public void setValue(int value)
	{
		this.value = value;
	}

	protected String type;
	
	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	public String generateData()
	{
		return "";
	}
	
	public int getIdSensor()
	{
		return idSensor;
	}
	
	public void setIdSensor( int idSensor )
	{
		this.idSensor = idSensor;
	}
}
