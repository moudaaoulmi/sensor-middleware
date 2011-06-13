package ontology.concepts.sensors;

import java.awt.font.NumericShaper;
import java.util.ArrayList;

import org.omg.CORBA.INTERNAL;
import org.w3c.dom.Element;

public class DummySensorsFactory 
{
	static protected DummySensorsFactory instance;
	
	protected ArrayList<ISensor> sensorsStream = new ArrayList<ISensor>();
	
	static private int startID = 1;
	
	private DummySensorsFactory()
	{
		
	}
	
	static public DummySensorsFactory getInstance()
	{
		if ( instance == null )
			instance = new DummySensorsFactory();
		
		return instance;
	}
	
	
	public ISensor createSensor( Element el )
	{
		ISensor sensor 		= null;
		String sensorType 	= el.getAttribute("type");
		String sensorId		= el.getAttribute("sensorId");
		int value 			= Integer.parseInt( el.getFirstChild().getNodeValue() );
		
		if ( sensorType.equals( SensorsTypes.DOOR_MOVEMENT) )
		{
			sensor = new DoorMovementSensor();
		}
		else if ( sensorType.equals( SensorsTypes.ELECTRIC))
		{
			sensor = new ElectricSensor();
		}
		else if ( sensorType.equals( SensorsTypes.HUMAN_MOVEMENT))
		{
			sensor = new HumanMovementSensor();
		}
		else if ( sensorType.equals( SensorsTypes.HUMIDITY))
		{
			sensor = new HumiditySensor();
		}
		else if ( sensorType.equals( SensorsTypes.LIGHT))
		{
			sensor = new LightSensor();
		}
		else if ( sensorType.equals( SensorsTypes.TEMPERATURE))
		{
			sensor = new TemperatureSensor();
		}
		else if ( sensorType.equals( SensorsTypes.WINDOW_MOVEMENT))
		{
			sensor = new WindowMovementSensor();
		}
		
		sensor.setType(sensorType);
		sensor.setIdSensor( Integer.parseInt(sensorId) );
		sensor.setValue(value);
		
		return sensor;
	}
}
