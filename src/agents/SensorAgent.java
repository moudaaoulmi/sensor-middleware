package agents;

import ontology.SensorsOntology;
import jade.content.lang.Codec;
import jade.content.lang.sl.SLCodec;
import jade.content.onto.Ontology;
import jade.core.Agent;

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
	
	public SensorAgent( String configFile )
	{
		parseConfigXML();
	}
	

	public void setup()
	{
		
	}

	protected void parseConfigXML()
	{
		// TODO Auto-generated method stub
		
	}
}
