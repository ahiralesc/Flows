package edu.cetys.icc.broker.information.data;

public class ComputeEPState extends Entity implements EndPointData {

	public float clockRate;
	
	public EndPointType endPointType;
	
	public float estAvgFlowTime;
	
	public float estAvgProcTime;
	
	public float estAvgWaitingTime;
	
	public int freeSlots;
	
	public State healthState;
	
	public int maxPreLRMSWaitingJobs;
	
	public int maxRunningJobs;
	
	public int maxTotalJobs;
	
	public int maxWaitingJobs;
	
	public int localRunningJobs;
	
	public int localSuspendedJobs;
	
	public int localWaitingJobs;
	
	public double p_max;
	
	public int preLRMSWaitingJobs;
	
	public int runningJobs;
	
	public int size;
	
	public int stagingJobs;
	
	public double sumFlowTimes;
	
	public double sumProcTimes;
	
	public double sumWaitingTimes;
	
	public int suspendedJobs;
	
	public int totalJobs;
	
	public int totalNumExecJobs;
	
	public int usedSlots;
	
	public int waitingJobs;
	
	public double coreEnergyConsumption;
	
	public ComputeEPState() {
		this.clockRate = -1;
		this.endPointType = EndPointType.UNDEFINED;
		this.estAvgFlowTime = -1;
		this.estAvgProcTime = -1;
		this.estAvgWaitingTime = -1;
		this.freeSlots = -1;
		this.healthState = State.UNKNOWN;
		this.maxPreLRMSWaitingJobs = -1;
		this.maxRunningJobs = -1;
		this.maxTotalJobs = -1;
		this.maxWaitingJobs = -1;
		this.localRunningJobs = -1;
		this.localSuspendedJobs = -1;
		this.localWaitingJobs = -1;
		this.p_max = -1;
		this.preLRMSWaitingJobs = -1;
		this.runningJobs = -1;
		this.size = -1;
		this.stagingJobs = -1;
		this.sumFlowTimes = -1;
		this.sumProcTimes = -1;
		this.sumWaitingTimes = -1;
		this.suspendedJobs = -1;
		this.totalJobs = -1;
		this.totalNumExecJobs = -1;
		this.usedSlots = -1;
		this.waitingJobs = -1;
		this.coreEnergyConsumption = -1;
		this.type = EntityType.STATUS;
	}
}
