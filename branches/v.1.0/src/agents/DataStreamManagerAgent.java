package agents;

import jade.content.lang.Codec;
import jade.content.lang.sl.SLCodec;
import jade.content.onto.Ontology;
import jade.core.Agent;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
import jade.wrapper.ControllerException;
import jade.wrapper.StaleProxyException;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import ontology.SensorsOntology;
import ontology.concepts.sensors.Sensor;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import behaviours.DataStreamManagerBehaviour;

public class DataStreamManagerAgent extends Agent
{
	protected List<SensorAgent> sensors;
	
	protected List<Integer> filteredIDs;
	protected List<String> filteredTypes;
	
	protected List<Sensor> sensorsDataBuffer;
	protected List<Sensor> interpretedDataBuffer;
	protected List<Sensor> aggregatedDataBuffer;
	
	private Codec codec 		= new SLCodec();
	private Ontology ontology 	= SensorsOntology.getInstace();
	
	public void setup()
	{
		sensors = new LinkedList<SensorAgent>();
		
		getContentManager().registerLanguage( codec );
		getContentManager().registerOntology( ontology );
		
		try
		{
			File file 					= new File( "config/sensorsFilter.xml" );
			DocumentBuilderFactory dbf 	= DocumentBuilderFactory.newInstance();
			DocumentBuilder db 			= dbf.newDocumentBuilder();
			Document filters			= db.parse( file );
			
			NodeList idNodeList = filters.getElementsByTagName("id");
			NodeList typeNodeList = filters.getElementsByTagName("type");
			
			filteredIDs 	= new LinkedList<Integer>();
			filteredTypes 	= new LinkedList<String>();
			
			for (int i = 0; i < idNodeList.getLength(); i++)
			{
				Element node = (Element) idNodeList.item( i );
				
				filteredIDs.add( Integer.parseInt( node.getFirstChild().getNodeValue()) );
			}
			
			for (int i = 0; i < typeNodeList.getLength(); i++)
			{
				Element node = (Element) typeNodeList.item( i );
				
				filteredTypes.add( node.getFirstChild().getNodeValue() );
			}
			//scenario.normalizeDocument();
		}
		catch (Exception e) 
		{
			// TODO: handle exception
			e.printStackTrace();
		}
		
		addBehaviour( new DataStreamManagerBehaviour() );
	}
	
	public SensorAgent getSensorAgentByID( Integer id )
	{
		for (SensorAgent sensorAgent : sensors)
		{
			if (sensorAgent.getId() == id)
				return sensorAgent;
		}
		return null;
	}
	
	public void addSensorAgent( SensorAgent sa )
	{
		ContainerController home = getContainerController();
		AgentController agentController;
		try
		{
			agentController = home.acceptNewAgent("sensorAgent"+sa.getId(), sa);
			agentController.start();
		} catch (StaleProxyException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		sensors.add(sa);
	}
	
	public void removeSensorAgent( Integer id )
	{
		ContainerController home = getContainerController();
		AgentController agentController;
		
		for (SensorAgent sa : sensors )
		{
			if ( sa.getId() == id )
			{
				sensors.remove(sa);

				try
				{
					agentController = home.getAgent( sa.getLocalName() );
					agentController.kill();
				} 
				catch (ControllerException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
