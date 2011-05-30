package ontology.concepts;

import jade.content.Concept;

public class Operators implements Concept 
{
	protected int a;
	public int getA() {
		return a;
	}

	public void setA(int a) {
		this.a = a;
	}

	public int getB() {
		return b;
	}

	public void setB(int b) {
		this.b = b;
	}

	protected int b;
	
	public Operators()
	{
	}
}
