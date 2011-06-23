package ontology.concepts.sensors;

import java.util.Date;
import java.util.GregorianCalendar;

import utils.DateUtil;

import jade.content.Concept;


public abstract class Sensor implements ISensor, Concept
{
	protected int idSensor;
	protected int value;
	protected Date time;
	protected String interpretedData;
	
	public Date getTime() 
	{
		return time;
	}

	public String getInterpretedData()
	{
		return interpretedData;
	}

	public void setInterpretedData(String interpretedData)
	{
		this.interpretedData = interpretedData;
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
	
	public String toInsertSQL()
	{
		String sql = "";
		
		sql = "INSERT INTO sensors_data (`idSensor`, `date`, `value`, `type`) VALUES " +
				"('"+idSensor+"', '"+DateUtil.toMySQLDate(time)+"', '"+value+"', '"+type+"' )";
		
		return sql;
	}
}
