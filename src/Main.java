import java.io.File;
import java.sql.Connection;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import agents.SensorAgent;

import database.DatabaseConfig;
import database.DatabaseConnection;


public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
//		DatabaseConnection db = new DatabaseConnection();
//		Connection conn;
//		try 
//		{
//			conn = db.connect(DatabaseConfig.HOST, DatabaseConfig.DB, DatabaseConfig.USERNAME, DatabaseConfig.PASSWORD);
//			db.executeQuery(conn, "SELECT * FROM users");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
		Document filters = null;
		try
		{
			File file 					= new File( "src/config/electric.xml" );
			DocumentBuilderFactory dbf 	= DocumentBuilderFactory.newInstance();
			DocumentBuilder db 			= dbf.newDocumentBuilder();
			filters						= db.parse( file );
			Node node;
			
			NodeList minFields = filters.getElementsByTagName("minValue");
			NodeList maxFields = filters.getElementsByTagName("maxValue");
			
			
			node = (Element) minFields.item( 0 );
			System.out.println( Integer.parseInt( node.getFirstChild().getNodeValue() ) );
			node = (Element) maxFields.item( 0 );
			System.out.println( Integer.parseInt( node.getFirstChild().getNodeValue() ) );
			node = (Element) minFields.item( 1 );
			System.out.println( node.getFirstChild().getNodeValue() );
			node = (Element) maxFields.item( 1 );
			System.out.println( node.getFirstChild().getNodeValue() );
			node = (Element) minFields.item( 2 );
			System.out.println( node.getFirstChild().getNodeValue() );
			node = (Element) maxFields.item( 2 );
			System.out.println( node.getFirstChild().getNodeValue() );
//			for (int i = 0; i < minFields.getLength(); i++)
//			{
//				Element node = (Element) minFields.item( i );
//				
//				System.out.println( node.getFirstChild().getNodeValue() );
//			}
			
		}
		catch (Exception e) 
		{
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}

}
