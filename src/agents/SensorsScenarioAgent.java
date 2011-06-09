package agents;

import behaviours.ScenarioStarterBehaviour;
import jade.core.Agent;

public class SensorsScenarioAgent extends Agent
{
	public void setup()
	{
		addBehaviour( new ScenarioStarterBehaviour() );
	}
}
