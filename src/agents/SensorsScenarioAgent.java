package agents;

import jade.content.lang.Codec;
import jade.content.lang.sl.SLCodec;
import jade.content.onto.Ontology;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import ontology.SensorsOntology;
import behaviours.ScenarioStarterBehaviour;

public class SensorsScenarioAgent extends Agent
{
	private Codec codec 		= new SLCodec();
	private Ontology ontology 	= SensorsOntology.getInstace();
	
	public void setup()
	{
		getContentManager().registerLanguage(codec);
		getContentManager().registerOntology(ontology);
		
		addBehaviour( new ScenarioStarterBehaviour() );
//		addBehaviour( new CyclicBehaviour()
//		{
//			
//			@Override
//			public void action()
//			{
//				// TODO Auto-generated method stub
//				ACLMessage msg = blockingReceive();
//			}
//		});
	}
}
