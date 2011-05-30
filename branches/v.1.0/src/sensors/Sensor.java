package sensors;

public abstract class Sensor implements ISensor
{
	protected int idSensor;
	protected int value;
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
	
	public void setIdSensor( int idSensor)
	{
		this.idSensor = idSensor;
	}
}
