package behaviours;

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
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import ontology.SensorsOntology;
import ontology.actions.InterpretData;
import ontology.actions.StoreInterpretedData;
import ontology.concepts.sensors.DoorMovementSensor;
import ontology.concepts.sensors.TemperatureSensor;

public class DoorMovementSensorBehaviour extends CyclicBehaviour
{
	private Codec codec 		= new SLCodec();
	private Ontology ontology 	= SensorsOntology.getInstace();
	
	@Override
	public void action()
	{
		System.out.println("Senzorul de temperatura asteapta date");
		ACLMessage msg = myAgent.receive();
		if (msg == null) { block(); return; }
		
		ContentElement content;
		try 
		{
			content = myAgent.getContentManager().extractContent(msg);
			Concept action = ((Action)content).getAction();

			switch (msg.getPerformative()) 
            {
               case (ACLMessage.INFORM ):
               {
            	   if ( action instanceof InterpretData )
            	   {
            		   //  create agent and create a factory for agent behaviour according to 
            		   // sensor type.
            		   DoorMovementSensor sensor = (DoorMovementSensor)((InterpretData) action).getSensor();
            		   
            		   handleSensorData( sensor );
            	   }
               }
            }
		} 
		catch (UngroundedException e) {
			e.printStackTrace();
		}
		catch (CodecException e) {
			e.printStackTrace();
		} 
		catch (OntologyException e) {
			e.printStackTrace();
		}
	}
	
	protected boolean isDataValid( DoorMovementSensor sensor )
	{
		SensorAgent sa = (SensorAgent)myAgent;
//		if ( sensor.getIdSensor() < sa.getMinId() || sensor.getIdSensor() > sa.getMaxId() )
//			return false;
//		if ( sensor.getValue() < sa.getMinValue() || sensor.getValue() > sa.getMaxValue())
//			return false;
		
		return true;
	}
	
	protected String interpretData( DoorMovementSensor sensor )
	{
		if ( sensor.getValue() ==  0 )
			return "inchis";
		return "deschis";
	}

	protected void handleSensorData(DoorMovementSensor sensor)
	{
		//validate data
		SensorAgent sa = (SensorAgent)myAgent;
		
		if ( isDataValid(sensor) )
		{
			sensor.setInterpretedData( interpretData(sensor) );
			sa.getData().add(sensor);
			//data interpretation
			AID receiverAID = new AID( "DataStreamManagerAgent", AID.ISLOCALNAME);
			
			ACLMessage message = new ACLMessage( ACLMessage.INFORM );
			
			message.setLanguage( codec.getName() );
			message.setOntology( ontology.getName() );
			message.addReceiver(receiverAID);
			
			StoreInterpretedData sid = new StoreInterpretedData();
			sid.setSensor( sensor );
			
			try
			{
				myAgent.getContentManager().fillContent(message, new Action(receiverAID, sid));
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
		else
		{
			// an error has occured in recived data
			AID receiverAID = new AID( "DataStreamManagerAgent", AID.ISLOCALNAME);
			
			ACLMessage message = new ACLMessage( ACLMessage.INFORM );
			
			message.setLanguage( codec.getName() );
			message.setOntology( ontology.getName() );
			message.addReceiver(receiverAID);
			
			StoreInterpretedData sid = new StoreInterpretedData();
			sid.setSensor( sensor );
			
			try
			{
				myAgent.getContentManager().fillContent(message, new Action(receiverAID, sid));
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
}
