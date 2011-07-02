package ontology.concepts.sensors;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import utils.DateUtil;

import jade.content.Concept;


public abstract class Sensor implements ISensor, Concept
{
	protected int idSensor;
	protected double value;
	protected Date time;
	protected int zoneID;
	
	protected String interpretedData;
	
	public Sensor()
	{
		Calendar currentDate 		= Calendar.getInstance();
		Date dateNow 				= new Date( currentDate.getTimeInMillis() );
		
		this.time = dateNow;
	}
	
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

	public double getValue()
	{
		return value;
	}

	public void setValue(double value)
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
	
	public int getZoneID()
	{
		return zoneID;
	}

	public void setZoneID(int zoneID)
	{
		this.zoneID = zoneID;
	}

	public String toInsertSQL()
	{
		String sql = "";
		
		sql = "INSERT INTO sensors_data (`idSensor`, `date`, `value`, `type`, `zoneId`) VALUES " +
				"('"+idSensor+"', '"+DateUtil.toMySQLDate(time)+"', '"+value+"', '"+type+"', '"+zoneID+"' )";
		
		return sql;
	}
}
