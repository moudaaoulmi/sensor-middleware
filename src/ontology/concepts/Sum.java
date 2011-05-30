package ontology.concepts;

import jade.content.Concept;

public class Sum implements Concept 
{
	protected int sum = 0;
	
	public int getSum() 
	{
		return sum;
	}

	public void setSum(int sum) 
	{
		this.sum = sum;
	}

	public Sum()
	{
		
	}
}
