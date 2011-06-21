package behaviours;

import agents.DataStreamManagerAgent;
import agents.SensorAgent;
import jade.content.Concept;
import jade.content.ContentElement;
import jade.content.lang.Codec;
import jade.content.lang.Codec.CodecException;
import jade.content.lang.sl.SLCodec;
import jade.content.onto.Ontology;
import jade.content.onto.OntologyException;
import jade.content.onto.UngroundedException;
import jade.content.onto.basic.Action;
import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import ontology.SensorsOntology;
import ontology.actions.InterpretData;
import ontology.actions.SensorDataRecived;
import ontology.concepts.sensors.ISensor;
import ontology.concepts.sensors.Sensor;
import utils.AgentFactory;

public class DataStreamManagerBehaviour extends CyclicBehaviour
{
	private Codec codec 		= new SLCodec();
	private Ontology ontology 	= SensorsOntology.getInstace();
	
	@Override
	public void action()
	{
		// TODO Auto-generated method stub
		System.out.println("Asteapta mesaj");
		ACLMessage msg = myAgent.receive();
		if (msg == null) { block(); return; }
		
		ContentElement content;
		try 
		{
			content = myAgent.getContentManager().extractContent(msg);
			Concept action = ((Action)content).getAction();
			System.out.println("Am primit mesaj");
			switch (msg.getPerformative()) 
            {
               case (ACLMessage.INFORM ):
               {
            	   if ( action instanceof SensorDataRecived )
            	   {
            		   //  create agent and create a factory for agent behaviour according to 
            		   // sensor type.
            		   Sensor sensor = ((SensorDataRecived) action).getSensor();
            		   
            		   handleSensorDataRecived( sensor );
            	   }
               }
            }
		} 
		catch (UngroundedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (CodecException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (OntologyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public DataStreamManagerAgent getDataStreamManager()
	{
		return (DataStreamManagerAgent) myAgent;
	}

	protected void handleSensorDataRecived(Sensor sensor)
	{
		SensorAgent sa 				= null; //AgentFactory.createAgent(sensor);
		DataStreamManagerAgent dsma = getDataStreamManager();  
		
		sa = dsma.getSensorAgentByID( sensor.getIdSensor() );
		
		if ( sa == null )
		{
			sa = AgentFactory.createAgent(sensor);
			dsma.addSensorAgent( sa );
		}
		
		System.out.println("Sensor type: "+sensor.getType());
		
		AID receiverAID = new AID( sa.getLocalName(), AID.ISLOCALNAME);
		
		ACLMessage message = new ACLMessage( ACLMessage.INFORM );
		
		message.setLanguage( codec.getName() );
		message.setOntology( ontology.getName() );
		message.addReceiver(receiverAID);
		
		InterpretData sdr = new InterpretData();
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
}
