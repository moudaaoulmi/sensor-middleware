package ontology.concepts.sensors;

import java.util.Date;
import java.util.GregorianCalendar;

import jade.content.Concept;


public abstract class Sensor implements ISensor, Concept
{
	protected int idSensor;
	protected int value;
	protected Date time;
	
	
	public Date getTime() 
	{
		return time;
	}

	public void setTime(Date time) 
	{
		this.time = time;
	}

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
