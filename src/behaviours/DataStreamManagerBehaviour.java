package behaviours;

import message.JessACLMessage;
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
import ontology.actions.AggregateData;
import ontology.actions.InterpretData;
import ontology.actions.SaveADToDB;
import ontology.actions.SaveDataToDB;
import ontology.actions.SensorDataRecived;
import ontology.actions.StoreAggregatedData;
import ontology.actions.StoreInterpretedData;
import ontology.concepts.AggregatedData;
import ontology.concepts.sensors.ISensor;
import ontology.concepts.sensors.Sensor;
import utils.AgentFactory;

public class DataStreamManagerBehaviour extends CyclicBehaviour
{
	public final long DATA_KEEPING_TIME = 30000;
	
	private Codec codec 		= new SLCodec();
	private Ontology ontology 	= SensorsOntology.getInstace();
	
	protected long sensorsDataBufferTime = 0; // keep the sensors data in memory for 30 seconds
	
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
            	   
            	   if ( action instanceof StoreInterpretedData )
            	   {
            		   Sensor sensor = ((StoreInterpretedData) action).getSensor();
            		   
            		   handleStoreInterpretedData( sensor );
            	   }
            	   
            	   if ( action instanceof StoreAggregatedData )
            	   {
            		   AggregatedData ad = (( StoreAggregatedData) action).getSensor();
            		   
            		   handleAggregatedData( ad );
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
			
			// Here will receive data from migrated sensor agents and dataaggregationagent
			System.out.println("Am primit de la JESS" + msg.getContent());
			//e.printStackTrace();
		} 
		catch (OntologyException e) 
		{
			// TODO Auto-generated catch block
			System.out.println("Am primit de la JESS" + msg.getContent());
			//e.printStackTrace();
		}

	}

	protected void handleAggregatedData(AggregatedData ad)
	{
		// TODO Auto-generated method stub
		DataStreamManagerAgent dsma = getDataStreamManager();
		
		dsma.getAggregatedDataBuffer().add(ad);
		
		AID receiverAID = new AID( "DatabaseManagerAgent", AID.ISLOCALNAME);
		
		ACLMessage message = new ACLMessage( ACLMessage.INFORM );
		
		message.setLanguage( codec.getName() );
		message.setOntology( ontology.getName() );
		message.addReceiver(receiverAID);
		
		SaveADToDB sdr = new SaveADToDB();
		sdr.setInfo(ad);
		
		
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
		
		addSensorDataToBuffer( sensor );
		
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
	
	protected void addSensorDataToBuffer(Sensor sensor)
	{
		// TODO Auto-generated method stub
		DataStreamManagerAgent dsma = getDataStreamManager();
		
		if ( sensorsDataBufferTime == 0 )
			sensorsDataBufferTime = System.currentTimeMillis();
		
		dsma.getSensorsDataBuffer().add(sensor);
		
		if ( sensorsDataBufferTime + DATA_KEEPING_TIME < System.currentTimeMillis() )
		{
			sensorsDataBufferTime = System.currentTimeMillis();
			dsma.getSensorsDataBuffer().clear();
		}
	}

	protected void handleStoreInterpretedData(Sensor sensor) 
	{
		// TODO Auto-generated method stub
		DataStreamManagerAgent dsma = getDataStreamManager();
		
		dsma.getInterpretedDataBuffer().add(sensor);
		
		AID receiverAID = new AID( "DatabaseManagerAgent", AID.ISLOCALNAME);
		
		ACLMessage message = new ACLMessage( ACLMessage.INFORM );
		
		message.setLanguage( codec.getName() );
		message.setOntology( ontology.getName() );
		message.addReceiver(receiverAID);
		
		SaveDataToDB sdr = new SaveDataToDB();
		sdr.setSql(sensor);
		
		AID receiverAID1 = new AID( "DataAggregationAgent", AID.ISLOCALNAME);
		
		ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
	
		msg.setLanguage( codec.getName() );
		msg.setOntology( ontology.getName() );
		msg.addReceiver(receiverAID1);
		
		AggregateData ad = new AggregateData();
		ad.setSensor(sensor);
		
		
		try
		{
			myAgent.getContentManager().fillContent(message, new Action(receiverAID, sdr));
			myAgent.send( message );
			
			myAgent.getContentManager().fillContent(msg, new Action(receiverAID1, ad));
			myAgent.send( msg );
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
