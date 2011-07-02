package ontology.concepts.sensors;

public interface ISensor 
{
	int getIdSensor();
	void setIdSensor( int id);
	
	String getType();
	void setType( String type);
	
	double getValue();
	void setValue( double value );
	
	int getZoneID();
	void setZoneID( int zoneId );
	
	String generateData();
}
