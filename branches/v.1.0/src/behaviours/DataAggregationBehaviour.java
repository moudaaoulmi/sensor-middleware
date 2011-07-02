package behaviours;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import message.JessACLMessage;

import jess.Fact;
import jess.Jesp;
import jess.JessException;
import jess.RU;
import jess.Rete;
import jess.Value;

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
	
	public boolean handleMessage( JessACLMessage message )
	{
		String sfact = "(assert (test ( id 3) (content 1)))"; // the fact must be constructed from the received message
		Fact fact = null;
		try
		{
			fact = new Fact("bogdan", reteEngine);
			fact.setSlotValue("id", new Value(1, RU.INTEGER));
			fact.setSlotValue("content", new Value(34, RU.INTEGER));
		} 
		catch (JessException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try
		{
			reteEngine.add(message);
		} catch (JessException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return addFact(fact);
	}

}
