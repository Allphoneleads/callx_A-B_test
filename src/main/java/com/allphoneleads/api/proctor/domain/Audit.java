package com.allphoneleads.api.proctor.domain;

public class Audit {
	
	private int version;
	private String updatedby;
	private long updated;
	
	
	public Audit(int version, String updatedby, long updated) {
		this.version = version;
		this.updatedby = updatedby;
		this.updated = updated;
	}
	
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	public String getUpdatedby() {
		return updatedby;
	}
	public void setUpdatedby(String updatedby) {
		this.updatedby = updatedby;
	}
	public long getUpdated() {
		return updated;
	}
	public void setUpdated(long updated) {
		this.updated = updated;
	}
	
	
	

}
