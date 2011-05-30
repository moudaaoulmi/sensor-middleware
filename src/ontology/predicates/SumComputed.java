package ontology.predicates;

import ontology.concepts.Sum;
import jade.content.Predicate;

public class SumComputed implements Predicate
{
	protected Sum sum; 
	
	public Sum getSum() 
	{
		return sum;
	}

	public void setSum(Sum sum) 
	{
		this.sum = sum;
	}

	public SumComputed()
	{
		
	}
	
	
}
