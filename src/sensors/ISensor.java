package sensors;

public interface ISensor 
{
	int getIdSensor();
	void setIdSensor( int id);
	
	String generateData();
}
