package com.fml.model;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class LeaseInfoModel {

	public String address;
	public String amount = "$";
	public String aptName;
	public int bathrooms;
	public int capacity;
	public String duration;
	public int existingMembers;
	public String leaseType;
	public String note;
	public String parking;
	public String petFriendly;
	public int rooms;
	
	public LeaseInfoModel(String address, String amount, String aptName, int bathrooms, int capacity,
			String duration, int existingMembers, String leaseType, String note, String parking, String petFriendly,
			int rooms) {
		super();
		this.address = address;
		this.amount = amount;
		this.aptName = aptName;
		this.bathrooms = bathrooms;
		this.capacity = capacity;
		this.duration = duration;
		this.existingMembers = existingMembers;
		this.leaseType = leaseType;
		this.note = note;
		this.parking = parking;
		this.petFriendly = petFriendly;
		this.rooms = rooms;
	}
	
	public LeaseInfoModel() {}

	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getAptName() {
		return aptName;
	}
	public void setAptName(String aptName) {
		this.aptName = aptName;
	}
	public int getBathrooms() {
		return bathrooms;
	}
	public void setBathrooms(int bathrooms) {
		this.bathrooms = bathrooms;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public int getExistingMembers() {
		return existingMembers;
	}
	public void setExistingMembers(int existingMembers) {
		this.existingMembers = existingMembers;
	}
	public String getLeaseType() {
		return leaseType;
	}
	public void setLeaseType(String leaseType) {
		this.leaseType = leaseType;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getParking() {
		return parking;
	}
	public void setParking(String parking) {
		this.parking = parking;
	}
	public String getPetFriendly() {
		return petFriendly;
	}
	public void setPetFriendly(String petFriendly) {
		this.petFriendly = petFriendly;
	}
	public int getRooms() {
		return rooms;
	}
	public void setRooms(int rooms) {
		this.rooms = rooms;
	}
	
	@Override
	public String toString() {
		return "LeaseInfoModel [address=" + address + ", amount=" + amount + ", aptName=" + aptName + ", bathrooms=" + bathrooms + ", capacity=" + capacity + ", duration=" + duration
				+ ", existingMembers=" + existingMembers + ", leaseType=" + leaseType + ", note=" + note + ", parking="
				+ parking + ", petFriendly=" + petFriendly + ", rooms=" + rooms + "]";
	}
	
}
