package edu.cetys.icc.broker.information.instrumentation;

import edu.cetys.icc.broker.information.data.State;

public abstract class AbstractInstrumentor implements Instrumentor {

	public abstract State getState();
	
	public abstract int getNumRunningJobs();
	
	public abstract int getNumWaitingJobs();
}
