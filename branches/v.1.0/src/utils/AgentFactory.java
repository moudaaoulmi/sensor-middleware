package utils;

import behaviours.TemperatureSensorBehaviour;
import ontology.concepts.sensors.Sensor;
import agents.SensorAgent;

public class AgentFactory
{

	static public SensorAgent createAgent( Sensor sensorType )
	{
		SensorAgent sensorAgent = null;
		
		if ( sensorType.getType().equals( SensorsTypes.DOOR_MOVEMENT) )
		{
			sensorAgent = new SensorAgent( "config/"+SensorsTypes.DOOR_MOVEMENT+".xml");
		}
		else if ( sensorType.getType().equals( SensorsTypes.ELECTRIC))
		{
			sensorAgent = new SensorAgent( "config/"+SensorsTypes.ELECTRIC+".xml");
		}
		else if ( sensorType.getType().equals( SensorsTypes.HUMAN_MOVEMENT))
		{
			sensorAgent = new SensorAgent( "config/"+SensorsTypes.HUMAN_MOVEMENT+".xml");
		}
		else if ( sensorType.getType().equals( SensorsTypes.HUMIDITY))
		{
			sensorAgent = new SensorAgent( "config/"+SensorsTypes.HUMIDITY+".xml");
		}
		else if ( sensorType.getType().equals( SensorsTypes.LIGHT))
		{
			sensorAgent = new SensorAgent( "config/"+SensorsTypes.LIGHT+".xml");
		}
		else if ( sensorType.getType().equals( SensorsTypes.TEMPERATURE))
		{
			sensorAgent = new SensorAgent( "config/"+SensorsTypes.TEMPERATURE+".xml");
			
			sensorAgent.addBehaviour( new TemperatureSensorBehaviour() );
		}
		else if ( sensorType.getType().equals( SensorsTypes.WINDOW_MOVEMENT))
		{
			sensorAgent = new SensorAgent( "config/"+SensorsTypes.WINDOW_MOVEMENT+".xml");
		}
		else if ( sensorType.getType().equals( SensorsTypes.RFID))
		{
			sensorAgent = new SensorAgent( "config/"+SensorsTypes.RFID+".xml");
		}
		else if ( sensorType.getType().equals( SensorsTypes.GAS))
		{
			sensorAgent = new SensorAgent( "config/"+SensorsTypes.GAS+".xml");
		}
		else if ( sensorType.getType().equals( SensorsTypes.WATER))
		{
			sensorAgent = new SensorAgent( "config/"+SensorsTypes.WATER+".xml");
		}
		
		sensorAgent.setId( sensorType.getIdSensor() );
		
		return sensorAgent;
	}
	
	
}
