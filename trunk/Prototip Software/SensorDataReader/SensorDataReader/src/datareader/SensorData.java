package datareader;

import java.util.StringTokenizer;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.ParseException;

/**
 * Holds a single sample of data from an OWL sensor
 */
public class SensorData
{
  private Date           date;
  private int            address;
  private double         amps;
  private String         model;
  private double         accumulatedAmps;
  
  /**
   * Create a sensor data object given the message received by the server.
   * 
   * @param serverMessage
   */
  public SensorData(String serverMessage) throws SensorDataFormatException
  {
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    boolean formatOK=false;
    int pos=serverMessage.indexOf('=');
    if( pos > 0 )
    {
      StringTokenizer strTok = new StringTokenizer(serverMessage.substring(pos+1), ",");
      if( strTok.countTokens() == 5 )
      {
        try
        {
          address = Integer.parseInt( strTok.nextToken() );
          amps = Double.parseDouble( strTok.nextToken() );
          model = strTok.nextToken();
          accumulatedAmps = Double.parseDouble( strTok.nextToken() );
          //Get the date at which this object was created
          String dateString = strTok.nextToken();     
          try
          {
            date = df.parse( dateString );
          }
          catch(ParseException e)
          {
            throw new SensorDataFormatException("Date format text format error: "+dateString);
          }
          formatOK=true;
        }
        catch(NumberFormatException e) {}
      }
    }
    if( !formatOK )
    {
      throw new SensorDataFormatException(serverMessage+" is not formatted correctly");
    }
  }
  
  public String toString()
  {
    StringBuffer strBuf = new StringBuffer();
    strBuf.append("DATE="+date);
    strBuf.append(",ADDR="+address);
    strBuf.append(",AMPS="+amps);
    strBuf.append(",MODEL="+model);
    strBuf.append(",ACCUMULATED_AMPS="+accumulatedAmps);
    return strBuf.toString();
  }
  
  /**
   * Return the address of the sensor that provided this data
   * @return
   */
  public int getAddress()
  {
    return address;
  }
  
  /**
   * Return the power in Amps
   * @return
   */
  public double getAmps()
  {
    return amps;
  }
  
  /**
   * Get the OWL model string.
   * 
   * @return
   */
  public String getModel()
  {
    return model;
  }
  
  /**
   * Get the accumulated amps field ?
   * 
   * @return
   */
  public double getAccumulatedAmps()
  {
    return accumulatedAmps;
  }
  
  /**
   * Get the date that the sensor data was read
   * 
   * @return
   */
  public Date getDate()
  {
    return date;
  }
  
  /**
   * Return the power in KW
   * @return
   */
  public double getKW(double voltage)
  {
    return (amps*voltage)/1E3;
  }
  
  public double getCost(double voltage, double costPerkWh)
  {
    double kw = getKW(voltage);
    return costPerkWh*kw;
  }
}
