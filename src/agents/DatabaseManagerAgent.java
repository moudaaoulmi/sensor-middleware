package agents;

import ontology.SensorsOntology;
import jade.content.lang.Codec;
import jade.content.lang.sl.SLCodec;
import jade.content.onto.Ontology;
import jade.core.Agent;

public class DatabaseManagerAgent extends Agent
{
	private Codec codec 		= new SLCodec();
	private Ontology ontology 	= SensorsOntology.getInstace();
	
	public void setup()
	{
		getContentManager().registerLanguage(codec);
		getContentManager().registerOntology(ontology);
		
	}
	
}
