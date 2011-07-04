package behaviours;

import jade.content.lang.Codec;
import jade.content.lang.Codec.CodecException;
import jade.content.lang.sl.SLCodec;
import jade.content.onto.Ontology;
import jade.content.onto.OntologyException;
import jade.content.onto.basic.Action;
import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
import jade.wrapper.StaleProxyException;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import ontology.SensorsOntology;
import ontology.actions.SensorDataRecived;
import ontology.concepts.sensors.Sensor;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import utils.DummySensorsFactory;
import agents.DataAggregationAgent;
import agents.DataStreamManagerAgent;
import agents.DatabaseManagerAgent;
import agents.ErrorDetectorAgent;

public class ScenarioStarterBehaviour extends Behaviour
{
	protected Document scenario;
	protected boolean isDone = false;
	
	private Codec codec 		= new SLCodec();
	private Ontology ontology 	= SensorsOntology.getInstace();
	
	public ScenarioStarterBehaviour()
	{
		try
		{
			File file 					= new File( "config/results_timo.xml" );
			DocumentBuilderFactory dbf 	= DocumentBuilderFactory.newInstance();
			DocumentBuilder db 			= dbf.newDocumentBuilder();
			scenario 					= db.parse( file );
			//scenario.normalizeDocument();
		}
		catch (Exception e) 
		{
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	@Override
	public void action()
	{
		ContainerController cc = myAgent.getContainerController();
		
		AgentController datastreamManager;
		AgentController databaseManager;
		AgentController dataAggregationManager;
		AgentController errorDetectorManager;
		
		try
		{
			datastreamManager = cc.createNewAgent("DataStreamManagerAgent", DataStreamManagerAgent.class.getName(), null);
			datastreamManager.start();
			
			databaseManager = cc.createNewAgent("DatabaseManagerAgent", DatabaseManagerAgent.class.getName(), null);
			databaseManager.start();
			
			dataAggregationManager = cc.createNewAgent("DataAggregationAgent", DataAggregationAgent.class.getName(), null);
			dataAggregationManager.start();
			
			errorDetectorManager = cc.createNewAgent("ErrorDetectorAgent", ErrorDetectorAgent.class.getName(), null);
			errorDetectorManager.start();
			
			Thread.sleep(2000);
			//System.out.println("Am instantiat agentul cu numarul "+ i);
		} catch (StaleProxyException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		NodeList nodeLst = scenario.getElementsByTagName("scenarioEntry");
		System.out.println("Scenario agent started.");
		for (int s = 0; s < nodeLst.getLength(); s++) 
		{
		   // System.out.println( nodeLst.getLength() );
		    Node fstNode = nodeLst.item(s);
		    
		    if (fstNode.getNodeType() == Node.ELEMENT_NODE) 
		    {
		    	Element fstElmnt 				= (Element) fstNode;
		    	NodeList sensorDataList = fstElmnt.getElementsByTagName("sensorData");
		    	
		    	for ( int j = 0; j < sensorDataList.getLength(); j++ )
		    	{
		    		Element sensorData = ( Element )sensorDataList.item(j);
		    		
		    		parseSensorDataNode( sensorData );
		    	}
		    	
					try
					{
						Thread.sleep(3000);
					} catch (InterruptedException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		    }

		}
		isDone = true;
	}
	
	public boolean done()
	{
		return isDone;
		
	}
	
	protected void parseSensorDataNode( Element sensorData )
	{
		Sensor sensor = (Sensor)DummySensorsFactory.getInstance().createSensor( sensorData );
		
		AID receiverAID = new AID("DataStreamManagerAgent", AID.ISLOCALNAME);
		
		ACLMessage message = new ACLMessage( ACLMessage.INFORM );
		
		message.setLanguage( codec.getName() );
		message.setOntology( ontology.getName() );
		message.addReceiver(receiverAID);
		
		SensorDataRecived sdr = new SensorDataRecived();
		sdr.setSensor( sensor );
		
		try
		{
			myAgent.getContentManager().fillContent(message, new Action(receiverAID, sdr));
			myAgent.send( message );
		} 
		catch (CodecException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (OntologyException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	protected void removeScenarioEntry()
	{
		
	}

}
