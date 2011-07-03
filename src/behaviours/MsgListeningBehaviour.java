package behaviours;

import message.JessACLMessage;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

public class MsgListeningBehaviour extends CyclicBehaviour
{
	protected DataAggregationBehaviour rb;
	
	public MsgListeningBehaviour( Agent agent, DataAggregationBehaviour rb )
	{
		super( agent );
		
		this.rb = rb;
	}
	
	@Override
	public void action()
	{
		// TODO Auto-generated method stub
		ACLMessage message = (ACLMessage) myAgent.receive();
		
		if ( message != null )
		{
			if ( rb.handleMessage( message ) )
			{
				//great success.
				System.out.println("Fact was added!");
			}
			else
			{
				System.out.println("An error has occured");
			}
		}
		else 
		{
			block();
		}
			

	}

}
