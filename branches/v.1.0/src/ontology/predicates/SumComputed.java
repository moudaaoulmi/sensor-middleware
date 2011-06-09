package ontology.predicates;

import ontology.concepts.SensorData;
import jade.content.Predicate;

public class SumComputed implements Predicate
{
	protected SensorData sum; 
	
	public SensorData getSum() 
	{
		return sum;
	}

	public void setSum(SensorData sum) 
	{
		this.sum = sum;
	}

	public SumComputed()
	{
		
	}
	
	
}
