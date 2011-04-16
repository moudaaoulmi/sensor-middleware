package datareader;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;


public class SocketDataReader extends Thread
{
	private Socket socket;
	private String data = "";
	private BufferedWriter bw; 
	private BufferedReader br;

	public void run()
	{	
		String line;
		
		try 
		{
			socket = new Socket("127.0.0.1", 12745);
			bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		}
		catch (UnknownHostException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		while(true)
		{
			try 
			{
				bw.write("GET\n");
				bw.flush();
				
				line = br.readLine();
				
				if ( line != null && line.indexOf("_COMPLETE") == -1)
				{
					//System.out.println(line);
					SensorData sensorData = new SensorData(line);
					System.out.println( sensorData.toString());
					//Reading the data from the sensors
					System.out.println("KW: " + sensorData.getKW( 230 ));
				}
				
				Thread.sleep(3000);
			} 
			catch (IOException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	public String getData()
	{
		return data;
	}
}
