package behaviours;

import jade.core.behaviours.Behaviour;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ScenarioStarterBehaviour extends Behaviour
{
	protected Document scenario;
	protected boolean isDone = false;
	
	public ScenarioStarterBehaviour()
	{
		try
		{
			File file 					= new File( "scenario.xml" );
			DocumentBuilderFactory dbf 	= DocumentBuilderFactory.newInstance();
			DocumentBuilder db 			= dbf.newDocumentBuilder();
			scenario 					= db.parse( file );
			//scenario.normalizeDocument();
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
		NodeList nodeLst = scenario.getElementsByTagName("scenarioEntry");
		System.out.println("Information of all employees");
		for (int s = 0; s < nodeLst.getLength(); s++) 
		{
		    System.out.println( nodeLst.getLength() );
		    Node fstNode = nodeLst.item(s);
		    
		    if (fstNode.getNodeType() == Node.ELEMENT_NODE) 
		    {
		    	Element fstElmnt 				= (Element) fstNode;
		    	NodeList sensorDataList = fstElmnt.getElementsByTagName("sensorData");
		    	
		    	for ( int j = 0; j < sensorDataList.getLength(); j++ )
		    	{
		    		Element sensorData = ( Element )sensorDataList.item(j);
		    		
		    		parseSensorDataNode( sensorData );
		    	}
		    	
//					try
//					{
//						Thread.sleep(1000);
//					} catch (InterruptedException e)
//					{
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
		    }

		}
		isDone = true;
	}
	
	public boolean done()
	{
		return isDone;
		
	}
	
	protected void parseSensorDataNode( Element sensorData )
	{
		System.out.print( sensorData.getAttribute("sensorId")+" ");
		System.out.print( sensorData.getAttribute("type"));
		System.out.println( sensorData.getFirstChild().getNodeValue() );
	}
	
	protected void removeScenarioEntry()
	{
		
	}

}
