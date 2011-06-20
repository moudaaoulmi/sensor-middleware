package behaviours;

import jade.content.Concept;
import jade.content.ContentElement;
import jade.content.lang.Codec.CodecException;
import jade.content.onto.OntologyException;
import jade.content.onto.UngroundedException;
import jade.content.onto.basic.Action;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import ontology.actions.SensorDataRecived;
import ontology.concepts.sensors.ISensor;
import ontology.concepts.sensors.Sensor;

public class DataStreamManagerBehaviour extends CyclicBehaviour
{

	@Override
	public void action()
	{
		// TODO Auto-generated method stub
		System.out.println("Asteapta mesaj");
		ACLMessage msg = myAgent.receive();
		if (msg == null) { block(); return; }
		System.out.println("Primeste mesaj");
		
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
            		   System.out.println("Sensor type: "+sensor.getType());
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
}
