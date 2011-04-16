package datareader;

import java.io.*;

/**
 * Thrown when a message is received from the OWL server with incorrect formatting.
 */
public class SensorDataFormatException extends IOException
{
	private static final long serialVersionUID = 1L;

	public SensorDataFormatException(String message)
	  {
	    super(message);
	  }
}
