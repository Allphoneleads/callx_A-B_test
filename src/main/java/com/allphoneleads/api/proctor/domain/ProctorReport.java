package com.allphoneleads.api.proctor.domain;

import java.io.Serializable;

public class ProctorReport implements Serializable {

	private static final long serialVersionUID = 2847781093042149830L;
	private long totalCalls;
	private long inactiveCalls;
	private long oldAlgoCalls;
	private long newAlgoCalls;
	private long oldAlgo2Calls;
	
	
	public long getTotalCalls() {
		return totalCalls;
	}
	public void setTotalCalls(long totalCalls) {
		this.totalCalls = totalCalls;
	}
	public long getOldAlgoCalls() {
		return oldAlgoCalls;
	}
	public void setOldAlgoCalls(long oldAlgoCalls) {
		this.oldAlgoCalls = oldAlgoCalls;
	}
	public long getNewAlgoCalls() {
		return newAlgoCalls;
	}
	public void setNewAlgoCalls(long newAlgoCalls) {
		this.newAlgoCalls = newAlgoCalls;
	}
	public long getInactiveCalls() {
		return inactiveCalls;
	}
	public void setInactiveCalls(long inactiveCalls) {
		this.inactiveCalls = inactiveCalls;
	}
	public long getOldAlgo2Calls() {
		return oldAlgo2Calls;
	}
	public void setOldAlgo2Calls(long oldAlgo2Calls) {
		this.oldAlgo2Calls = oldAlgo2Calls;
	}
	
	
	
	

}
