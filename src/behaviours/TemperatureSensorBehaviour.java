package behaviours;

import ontology.SensorsOntology;
import ontology.actions.InterpretData;
import ontology.actions.SensorDataRecived;
import ontology.concepts.sensors.Sensor;
import ontology.concepts.sensors.TemperatureSensor;
import jade.content.Concept;
import jade.content.ContentElement;
import jade.content.lang.Codec;
import jade.content.lang.Codec.CodecException;
import jade.content.lang.sl.SLCodec;
import jade.content.onto.Ontology;
import jade.content.onto.OntologyException;
import jade.content.onto.UngroundedException;
import jade.content.onto.basic.Action;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

public class TemperatureSensorBehaviour extends CyclicBehaviour
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
            		   TemperatureSensor sensor = (TemperatureSensor)((InterpretData) action).getSensor();
            		   
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

	protected void handleSensorData(TemperatureSensor sensor)
	{
		//validate data
		System.out.println("Datele despre temperatura au fost validate");
		//data interpretation
		System.out.println("Datele despre temperatura au fost interpretates");
		
	}
}
