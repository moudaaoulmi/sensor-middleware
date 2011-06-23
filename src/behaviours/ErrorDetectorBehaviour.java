package behaviours;

import ontology.actions.HandleBadData;
import ontology.actions.SensorDataRecived;
import ontology.actions.StoreInterpretedData;
import ontology.concepts.sensors.Sensor;
import jade.content.Concept;
import jade.content.ContentElement;
import jade.content.lang.Codec.CodecException;
import jade.content.onto.OntologyException;
import jade.content.onto.UngroundedException;
import jade.content.onto.basic.Action;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

public class ErrorDetectorBehaviour extends CyclicBehaviour
{

	@Override
	public void action()
	{
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
            	   if ( action instanceof HandleBadData )
            	   {
            		   //  create agent and create a factory for agent behaviour according to 
            		   // sensor type.
            		   Sensor sensor = ((HandleBadData) action).getSensor();
            		   
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
