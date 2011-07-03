package jess;

import ontology.SensorsOntology;
import ontology.actions.StoreAggregatedData;
import ontology.concepts.AggregatedData;
import jade.content.lang.Codec;
import jade.content.lang.Codec.CodecException;
import jade.content.lang.sl.SLCodec;
import jade.content.onto.Ontology;
import jade.content.onto.OntologyException;
import jade.content.onto.basic.Action;
import jade.core.AID;
import jade.core.Agent;
import jade.lang.acl.ACLMessage;

public class JessSend implements Userfunction
{
	protected Agent myAgent;
	
	private Codec codec 		= new SLCodec();
	private Ontology ontology 	= SensorsOntology.getInstace();
	
	public JessSend( Agent a )
	{
		this.myAgent = a;
	}
	
	@Override
	public Value call(ValueVector vv, Context context) throws JessException
	{
		// TODO Auto-generated method stub
		
		Value v = vv.get(1);
		
		ACLMessage msg = new ACLMessage( ACLMessage.INFORM );
		AID receiverAID = new AID("DataStreamManagerAgent", AID.ISLOCALNAME);
		
		msg.setLanguage(codec.getName());
		msg.setOntology(ontology.getName());
		//msg.setContent( v.toString() );
		msg.addReceiver( receiverAID );
		
		StoreAggregatedData sad = new StoreAggregatedData();
		AggregatedData ad = new AggregatedData();
		
		ad.setInfo( v.toString() );
		sad.setSensor(ad);
		
		
		try
		{
			myAgent.getContentManager().fillContent(msg, new Action(receiverAID, sad));
			myAgent.send( msg );
		} catch (CodecException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (OntologyException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		
		return Funcall.TRUE;
	}

	@Override
	public String getName()
	{
		// TODO Auto-generated method stub
		return ("send");
	}

}
