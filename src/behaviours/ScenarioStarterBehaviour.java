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

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import ontology.SensorsOntology;
import ontology.actions.SensorDataRecived;
import ontology.concepts.sensors.DummySensorsFactory;
import ontology.concepts.sensors.ISensor;
import ontology.concepts.sensors.Sensor;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

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
			File file 					= new File( "config/scenario.xml" );
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
		NodeList nodeLst = scenario.getElementsByTagName("scenarioEntry");
		System.out.println("Information of all employees");
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
		    	
//					try
//					{
//						Thread.sleep(1000);
//					} catch (InterruptedException e)
//					{
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
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
