package behaviours;

import java.sql.Connection;

import database.DatabaseConfig;
import database.DatabaseConnection;
import agents.DatabaseManagerAgent;
import ontology.SensorsOntology;
import ontology.actions.SaveDataToDB;
import ontology.actions.SensorDataRecived;
import ontology.actions.StoreInterpretedData;
import ontology.concepts.sensors.Sensor;
import jade.content.Concept;
import jade.content.ContentElement;
import jade.content.lang.Codec;
import jade.content.lang.Codec.CodecException;
import jade.content.lang.sl.SLCodec;
import jade.content.onto.Ontology;
import jade.content.onto.OntologyException;
import jade.content.onto.UngroundedException;
import jade.content.onto.basic.Action;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

public class DBManagerBehaviour extends CyclicBehaviour
{
	private Codec codec 		= new SLCodec();
	private Ontology ontology 	= SensorsOntology.getInstace();
	
	protected DatabaseConnection dbConn;
	protected Connection conn;
	
	public DBManagerBehaviour()
	{
		dbConn = new DatabaseConnection();
		
		try 
		{
			conn  = dbConn.connect(DatabaseConfig.HOST, DatabaseConfig.DB, DatabaseConfig.USERNAME, DatabaseConfig.PASSWORD);
		} 
		catch (Exception e) 
		{
			System.out.println("Agent cannot connect to database!");
			e.printStackTrace();
		}

	}
	
	@Override
	public void action()
	{
		System.out.println("DB Manager asteapta mesaj");
		
		ACLMessage msg = myAgent.receive();
		if (msg == null) { block(); return; }
		
		ContentElement content;
		try 
		{
			content = myAgent.getContentManager().extractContent(msg);
			Concept action = ((Action)content).getAction();
			System.out.println("Am primit mesaj");
			switch (msg.getPerformative()) 
            {
               case (ACLMessage.INFORM ):
               {
            	   if ( action instanceof SaveDataToDB )
            	   {
            		   //  create agent and create a factory for agent behaviour according to 
            		   // sensor type.
            		   Sensor sql = ((SaveDataToDB) action).getSql();
            		   dbConn.executeInsertQuery( conn, sql.toInsertSQL() );
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
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public DatabaseManagerAgent getDatabaseManagerAgent()
	{
		return (DatabaseManagerAgent)myAgent;
	}
}
