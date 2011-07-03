package behaviours;

import jade.content.Concept;
import jade.content.ContentElement;
import jade.content.lang.Codec.CodecException;
import jade.content.onto.OntologyException;
import jade.content.onto.UngroundedException;
import jade.content.onto.basic.Action;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import jess.Fact;
import jess.Jesp;
import jess.JessException;
import jess.JessSend;
import jess.RU;
import jess.Rete;
import jess.Value;
import message.JessACLMessage;
import ontology.actions.AggregateData;
import ontology.concepts.sensors.ISensor;

public class DataAggregationBehaviour extends CyclicBehaviour
{
	static protected final int MAX_JASS_PASSES = 1;
	protected Rete reteEngine;
	
	public DataAggregationBehaviour( Agent a, String jessFile )
	{
		super( a );
		
		reteEngine = new Rete();
		FileReader fr;
		
		try
		{
			fr = new FileReader(jessFile);
			Jesp jesp = new Jesp(fr, reteEngine);
			
			jesp.parse(false);	
			
			reteEngine.addUserfunction( new JessSend(myAgent));
			
			fr.close();
		} 
		catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JessException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	@Override
	public void action()
	{
		// TODO Auto-generated method stub
		int executedPasses = -1;
		
		try
		{
			executedPasses = reteEngine.run(MAX_JASS_PASSES);
		} catch (JessException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if ( executedPasses < MAX_JASS_PASSES )
			block();
	}
	
	public boolean addFact( Fact jessFact )
	{
		try
		{
			reteEngine.assertFact(jessFact);
		}
		catch (JessException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		if ( !isRunnable() )
			restart();
		
		return true;
		
	}
	
	public boolean handleMessage( ACLMessage message )
	{
		Fact fact = null;
		ContentElement content;
		try 
		{
			content = myAgent.getContentManager().extractContent(message);
			Concept action = ((Action)content).getAction();
			System.out.println("Am primit mesaj");
			switch (message.getPerformative()) 
            {
               case (ACLMessage.INFORM):
               {
            	   if ( action instanceof AggregateData )
            	   {
            		   ISensor sensor = ((AggregateData) action).getSensor();
            		   //System.out.println("Suma este: "+sum);
            		   
            		   	fact = new Fact("sensor", reteEngine);
           				fact.setSlotValue("id", new Value(sensor.getIdSensor(), RU.INTEGER));
           				fact.setSlotValue("value", new Value(sensor.getValue(), RU.FLOAT));
           				fact.setSlotValue("type", new Value(sensor.getType(), RU.STRING));
           				fact.setSlotValue("zoneId", new Value(sensor.getZoneID(), RU.INTEGER));
           				//fact.setSlotValue("date", new Value(sensor.getType(), RU.d));
           				//fact.setSlotValue("interpretedData", new Value(sensor., RU.STRING));
           				return addFact(fact);
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
		} catch (JessException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}

}
