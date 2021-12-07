package com.fire.model;

public class LruModel {

	public String leaseId;
	public String status;
	
	public String getLeaseId() {
		return leaseId;
	}
	public void setLeaseId(String leaseId) {
		this.leaseId = leaseId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	@Override
	public String toString() {
		return "LroModel [leaseId=" + leaseId + ", status=" + status + "]";
	}
	
}
