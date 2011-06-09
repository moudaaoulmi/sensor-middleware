package behaviours;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

import jade.core.behaviours.CyclicBehaviour;

public class ScenarioStarterBehaviour extends CyclicBehaviour
{
	protected Document scenario;
	
	public ScenarioStarterBehaviour()
	{
		try
		{
			File file 					= new File( "scenario.xml" );
			DocumentBuilderFactory dbf 	= DocumentBuilderFactory.newInstance();
			DocumentBuilder db 			= dbf.newDocumentBuilder();
			scenario 					= db.parse( file );
			scenario.normalizeDocument();
		}
		catch (Exception e) 
		{
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	@Override
	public void action()
	{
		// TODO Auto-generated method stub

	}
	
	protected void removeScenarioEntry()
	{
		
	}

}
