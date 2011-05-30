package ontology.actions;

import ontology.concepts.Operators;
import jade.content.AgentAction;
import jade.content.onto.basic.Action;

public class MakeSum implements AgentAction
{
	protected Operators op;
	
	public Operators getOp() {
		return op;
	}

	public void setOp(Operators op) {
		this.op = op;
	}

	public MakeSum( )
	{
	}
	
	
}
