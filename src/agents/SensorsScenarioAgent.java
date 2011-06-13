package agents;

import jade.content.lang.Codec;
import jade.content.lang.sl.SLCodec;
import jade.content.onto.Ontology;
import jade.core.Agent;
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
	}
}
