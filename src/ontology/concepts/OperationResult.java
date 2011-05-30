package ontology.concepts;

import jade.content.Concept;

public class OperationResult implements Concept 
{
	protected int result = 0;

	public int getResult() 
	{
		return result;
	}

	public void setResult(int result) 
	{
		this.result = result;
	}
}
