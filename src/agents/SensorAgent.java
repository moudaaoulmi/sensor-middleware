package agents;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import jade.content.lang.Codec;
import jade.content.lang.sl.SLCodec;
import jade.content.onto.Ontology;
import jade.core.Agent;
import ontology.SensorsOntology;
import ontology.concepts.sensors.Sensor;

public class SensorAgent extends Agent
{
	private Codec codec 		= new SLCodec();
	private Ontology ontology 	= SensorsOntology.getInstace();
	
	protected String time;
	protected Integer minTime;
	protected Integer maxTime;
	
	protected double value;
	protected double minValue;
	protected double maxValue;
	
	protected Integer idSensor;
	protected Integer minId;
	protected Integer maxId;
	
	protected List<Sensor> data;
	
	public List<Sensor> getData()
	{
		return data;
	}


	public void setData(List<Sensor> data)
	{
		this.data = data;
	}


	public SensorAgent( String configFile )
	{
		parseConfigXML( configFile );
		
		data = new LinkedList<Sensor>();
	}
	

	public void setup()
	{
		getContentManager().registerLanguage( codec );
		getContentManager().registerOntology( ontology );
	}

	public Integer getId()
	{
		return idSensor;
	}

	public void setId(Integer idSensor)
	{
		this.idSensor = idSensor;
	}
	
	public Integer getMinTime()
	{
		return minTime;
	}


	public void setMinTime(Integer minTime)
	{
		this.minTime = minTime;
	}


	public Integer getMaxTime()
	{
		return maxTime;
	}


	public void setMaxTime(Integer maxTime)
	{
		this.maxTime = maxTime;
	}


	public double getMinValue()
	{
		return minValue;
	}


	public void setMinValue(Integer minValue)
	{
		this.minValue = minValue;
	}


	public double getMaxValue()
	{
		return maxValue;
	}


	public void setMaxValue(Integer maxValue)
	{
		this.maxValue = maxValue;
	}


	public Integer getMinId()
	{
		return minId;
	}


	public void setMinId(Integer minId)
	{
		this.minId = minId;
	}


	public Integer getMaxId()
	{
		return maxId;
	}


	public void setMaxId(Integer maxId)
	{
		this.maxId = maxId;
	}
	
	protected void parseConfigXML( String xmlFile )
	{
		// TODO Auto-generated method stub
		try
		{
			File file 					= new File( xmlFile );
			DocumentBuilderFactory dbf 	= DocumentBuilderFactory.newInstance();
			DocumentBuilder db 			= dbf.newDocumentBuilder();
			Document constraints		= db.parse( file );
			Element node;
			NodeList minFields = constraints.getElementsByTagName("minValue");
			NodeList maxFields = constraints.getElementsByTagName("maxValue");
			
			node = (Element) minFields.item( 0 );
			minTime = Integer.parseInt( node.getFirstChild().getNodeValue() );
			node = (Element) maxFields.item( 0 );
			maxTime = Integer.parseInt( node.getFirstChild().getNodeValue() );
			node = (Element) minFields.item( 1 );
			minValue = Integer.parseInt( node.getFirstChild().getNodeValue() );
			node = (Element) maxFields.item( 1 );
			maxValue = Integer.parseInt( node.getFirstChild().getNodeValue() );
			node = (Element) minFields.item( 2 );
			minId = Integer.parseInt( node.getFirstChild().getNodeValue() );
			node = (Element) maxFields.item( 2 );
			maxId = Integer.parseInt( node.getFirstChild().getNodeValue() );
		} 
		catch (ParserConfigurationException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
