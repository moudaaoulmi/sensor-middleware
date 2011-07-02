package utils;

import java.awt.font.NumericShaper;
import java.util.ArrayList;

import ontology.concepts.sensors.DoorMovementSensor;
import ontology.concepts.sensors.ElectricSensor;
import ontology.concepts.sensors.GasSensor;
import ontology.concepts.sensors.HumanMovementSensor;
import ontology.concepts.sensors.HumiditySensor;
import ontology.concepts.sensors.ISensor;
import ontology.concepts.sensors.LightSensor;
import ontology.concepts.sensors.RFIDSensor;
import ontology.concepts.sensors.TemperatureSensor;
import ontology.concepts.sensors.WaterSensor;
import ontology.concepts.sensors.WindowMovementSensor;

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
		int    zoneId		= Integer.parseInt( el.getAttribute("zoneId") );
		double value 		= Double.parseDouble( el.getFirstChild().getNodeValue() );
		
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
		else if ( sensorType.equals( SensorsTypes.RFID))
		{
			sensor = new RFIDSensor();
		}
		else if ( sensorType.equals( SensorsTypes.GAS))
		{
			sensor = new GasSensor();
		}
		else if ( sensorType.equals( SensorsTypes.WATER))
		{
			sensor = new WaterSensor();
		}
	
		sensor.setType(sensorType);
		sensor.setIdSensor( Integer.parseInt(sensorId) );
		sensor.setValue(value);
		sensor.setZoneID(zoneId);
		
		return sensor;
	}
}
