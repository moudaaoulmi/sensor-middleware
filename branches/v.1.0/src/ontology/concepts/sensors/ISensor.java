package ontology.concepts.sensors;

public interface ISensor 
{
	int getIdSensor();
	void setIdSensor( int id);
	
	String getType();
	void setType( String type);
	
	int getValue();
	void setValue( int value );
	
	String generateData();
}
