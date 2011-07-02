package utils;

import behaviours.DoorMovementSensorBehaviour;
import behaviours.ElectricSensorBehaviour;
import behaviours.GasSensorBehaviour;
import behaviours.HumanMovementSensorBehaviour;
import behaviours.HumiditySensorBehaviour;
import behaviours.LightSensorBehaviour;
import behaviours.RFIDSensorBehaviour;
import behaviours.TemperatureSensorBehaviour;
import behaviours.WaterSensorBehaviour;
import behaviours.WindowMovementSensorBehaviour;
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
			sensorAgent.addBehaviour( new DoorMovementSensorBehaviour() );
		}
		else if ( sensorType.getType().equals( SensorsTypes.ELECTRIC))
		{
			sensorAgent = new SensorAgent( "config/"+SensorsTypes.ELECTRIC+".xml");
			sensorAgent.addBehaviour( new ElectricSensorBehaviour() );
		}
		else if ( sensorType.getType().equals( SensorsTypes.HUMAN_MOVEMENT))
		{
			sensorAgent = new SensorAgent( "config/"+SensorsTypes.HUMAN_MOVEMENT+".xml");
			sensorAgent.addBehaviour( new HumanMovementSensorBehaviour() );
		}
		else if ( sensorType.getType().equals( SensorsTypes.HUMIDITY))
		{
			sensorAgent = new SensorAgent( "config/"+SensorsTypes.HUMIDITY+".xml");
			sensorAgent.addBehaviour( new HumiditySensorBehaviour() );
		}
		else if ( sensorType.getType().equals( SensorsTypes.LIGHT))
		{
			sensorAgent = new SensorAgent( "config/"+SensorsTypes.LIGHT+".xml");
			sensorAgent.addBehaviour( new LightSensorBehaviour() );
		}
		else if ( sensorType.getType().equals( SensorsTypes.TEMPERATURE))
		{
			sensorAgent = new SensorAgent( "config/"+SensorsTypes.TEMPERATURE+".xml");
			
			sensorAgent.addBehaviour( new TemperatureSensorBehaviour() );
		}
		else if ( sensorType.getType().equals( SensorsTypes.WINDOW_MOVEMENT))
		{
			sensorAgent = new SensorAgent( "config/"+SensorsTypes.WINDOW_MOVEMENT+".xml");
			sensorAgent.addBehaviour( new WindowMovementSensorBehaviour() );
		}
		else if ( sensorType.getType().equals( SensorsTypes.RFID))
		{
			sensorAgent = new SensorAgent( "config/"+SensorsTypes.RFID+".xml");
			sensorAgent.addBehaviour( new RFIDSensorBehaviour() );
		}
		else if ( sensorType.getType().equals( SensorsTypes.GAS))
		{
			sensorAgent = new SensorAgent( "config/"+SensorsTypes.GAS+".xml");
			sensorAgent.addBehaviour( new GasSensorBehaviour() );
		}
		else if ( sensorType.getType().equals( SensorsTypes.WATER))
		{
			sensorAgent = new SensorAgent( "config/"+SensorsTypes.WATER+".xml");
			sensorAgent.addBehaviour( new WaterSensorBehaviour() );
		}
		
		sensorAgent.setId( sensorType.getIdSensor() );
		
		return sensorAgent;
	}
	
	
}
