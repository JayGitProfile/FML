package com.fire.model;

public class LroModel {

	public String userId;
	public String status;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	@Override
	public String toString() {
		return "LroModel [userId=" + userId + ", status=" + status + "]";
	}
	
}
