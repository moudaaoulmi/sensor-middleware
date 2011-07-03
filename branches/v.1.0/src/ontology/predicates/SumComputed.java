package ontology.predicates;

import ontology.concepts.AggregatedData;
import jade.content.Predicate;

public class SumComputed implements Predicate
{
	protected AggregatedData sum; 
	
	public AggregatedData getSum() 
	{
		return sum;
	}

	public void setSum(AggregatedData sum) 
	{
		this.sum = sum;
	}

	public SumComputed()
	{
		
	}
	
	
}
