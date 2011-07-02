package agents;

import ontology.SensorsOntology;
import behaviours.DBManagerBehaviour;
import behaviours.DataAggregationBehaviour;
import behaviours.MsgListeningBehaviour;
import jade.content.lang.Codec;
import jade.content.lang.sl.SLCodec;
import jade.content.onto.Ontology;
import jade.core.Agent;

public class DataAggregationAgent extends Agent
{
	private Codec codec 		= new SLCodec();
	private Ontology ontology 	= SensorsOntology.getInstace();
	
	public void setup()
	{
		getContentManager().registerLanguage(codec);
		getContentManager().registerOntology(ontology);
		
		//add here specific behaviour
		DataAggregationBehaviour dab = new DataAggregationBehaviour(this, "jess/jess.clp");
		addBehaviour(dab);
		addBehaviour( new MsgListeningBehaviour(this, dab));
	}
}
