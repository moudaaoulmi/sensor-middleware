package agents;

import java.util.LinkedList;
import java.util.List;

import jade.content.lang.Codec;
import jade.content.lang.sl.SLCodec;
import jade.content.onto.Ontology;
import jade.core.Agent;
import ontology.SensorsOntology;
import ontology.concepts.sensors.Sensor;

public class SensorAgent extends Agent
{
	private Codec codec 		= new SLCodec();
	private Ontology ontology 	= SensorsOntology.getInstace();
	
	protected String time;
	protected Integer minTime;
	protected Integer maxTime;
	
	protected Integer value;
	protected Integer minValue;
	protected Integer maxValue;
	
	protected Integer idSensor;
	protected Integer minId;
	protected Integer maxId;
	
	protected List<Sensor> data;
	
	public List<Sensor> getData()
	{
		return data;
	}


	public void setData(List<Sensor> data)
	{
		this.data = data;
	}


	public SensorAgent( String configFile )
	{
		parseConfigXML();
		
		data = new LinkedList<Sensor>();
	}
	

	public void setup()
	{
		getContentManager().registerLanguage( codec );
		getContentManager().registerOntology( ontology );
	}

	public Integer getId()
	{
		return idSensor;
	}

	public void setId(Integer idSensor)
	{
		this.idSensor = idSensor;
	}
	
	public Integer getMinTime()
	{
		return minTime;
	}


	public void setMinTime(Integer minTime)
	{
		this.minTime = minTime;
	}


	public Integer getMaxTime()
	{
		return maxTime;
	}


	public void setMaxTime(Integer maxTime)
	{
		this.maxTime = maxTime;
	}


	public Integer getMinValue()
	{
		return minValue;
	}


	public void setMinValue(Integer minValue)
	{
		this.minValue = minValue;
	}


	public Integer getMaxValue()
	{
		return maxValue;
	}


	public void setMaxValue(Integer maxValue)
	{
		this.maxValue = maxValue;
	}


	public Integer getMinId()
	{
		return minId;
	}


	public void setMinId(Integer minId)
	{
		this.minId = minId;
	}


	public Integer getMaxId()
	{
		return maxId;
	}


	public void setMaxId(Integer maxId)
	{
		this.maxId = maxId;
	}
	
	protected void parseConfigXML()
	{
		// TODO Auto-generated method stub
		
	}
}
