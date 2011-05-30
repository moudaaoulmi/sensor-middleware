package sensors;

import java.util.ArrayList;

public class DummySensorsManager 
{
	static protected DummySensorsManager instance;
	
	static protected final int DOOR_MOVEMENT_SENSOR_NO 		= 4;
	static protected final int WINDOW_MOVEMENT_SENSOR_NO 	= 4;
	static protected final int HUMAN_MOVEMENT_SENSOR_NO 	= 4;
	static protected final int ELECTRIC_SENSOR_NO 			= 4;
	static protected final int HUMIDITY_SENSOR_NO 			= 4;
	static protected final int LIGHT_SENSOR_NO 				= 4;
	static protected final int TEMPERATURE_SENSOR_NO 		= 4;
	
	protected ArrayList<ISensor> sensorsStream = new ArrayList<ISensor>();
	
	static private int startID = 1;
	
	private DummySensorsManager()
	{
		
	}
	
	static public DummySensorsManager getInstance()
	{
		if ( instance == null )
			instance = new DummySensorsManager();
		
		return instance;
	}
	
	public ArrayList<ISensor> createCreateStream( )
	{
		Sensor sensor;
		for (int i = 0; i < DOOR_MOVEMENT_SENSOR_NO; i++)
		{
			sensor = new DoorMovementSensor();
			
			sensor.setIdSensor( startID++ );
			sensor.setType( SensorsTypes.DOOR_MOVEMENT );
			
			sensorsStream.add( sensor );
		}
		
		for (int i = 0; i < ELECTRIC_SENSOR_NO; i++)
		{
			sensor = new ElectricSensor();
			
			sensor.setIdSensor( startID++ );
			sensor.setType( SensorsTypes.ELECTRIC );
			
			sensorsStream.add( sensor );
		}
		
		for (int i = 0; i < HUMAN_MOVEMENT_SENSOR_NO; i++)
		{
			sensor = new HumanMovementSensor();
			
			sensor.setIdSensor( startID++ );
			sensor.setType( SensorsTypes.HUMAN_MOVEMENT );
			
			sensorsStream.add( sensor );
		}
		
		for (int i = 0; i < HUMIDITY_SENSOR_NO; i++)
		{
			sensor = new HumiditySensor();
			
			sensor.setIdSensor( startID++ );
			sensor.setType( SensorsTypes.HUMIDITY );
			
			sensorsStream.add( sensor );
		}
		
		for (int i = 0; i < LIGHT_SENSOR_NO; i++)
		{
			sensor = new LightSensor();
			
			sensor.setIdSensor( startID++ );
			sensor.setType( SensorsTypes.LIGHT );
			
			sensorsStream.add( sensor );
		}
		
		for (int i = 0; i < TEMPERATURE_SENSOR_NO; i++)
		{
			sensor = new DoorMovementSensor();
			
			sensor.setIdSensor( startID++ );
			sensor.setType( SensorsTypes.TEMPERATURE );
			
			sensorsStream.add( sensor );
		}
		
		for (int i = 0; i < WINDOW_MOVEMENT_SENSOR_NO; i++)
		{
			sensor = new DoorMovementSensor();
			
			sensor.setIdSensor( startID++ );
			sensor.setType( SensorsTypes.WINDOW_MOVEMENT );
			
			sensorsStream.add( sensor );
		}
		
		
		return sensorsStream;
	}
}
