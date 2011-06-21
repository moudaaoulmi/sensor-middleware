package ontology;

import jade.content.onto.BeanOntology;
import jade.content.onto.BeanOntologyException;
import jade.content.onto.Ontology;


public class SensorsOntology extends BeanOntology 
{
	public static final String ONTOLOGY_NAME = "SensorsOntology";
	
	private static Ontology theInstance = new SensorsOntology(ONTOLOGY_NAME); 
	
	public static Ontology getInstace()
	{
		return theInstance;
	}
	
	private SensorsOntology(String ontologyName) 
	{
		super(ontologyName);
		
		try 
		{
			add("ontology.actions");
			add("ontology.concepts");
			add("ontology.concepts.sensors");
			add("ontology.predicates");
		} 
		catch (BeanOntologyException e) 
		{
			e.printStackTrace();
		}
	}

}
