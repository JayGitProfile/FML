package com.fml.model;

import java.util.ArrayList;
import java.util.List;

import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.QueryDocumentSnapshot;

public class LeaseInfoModel {

	public String docId;
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
	
	public LeaseInfoModel(QueryDocumentSnapshot document) {
		this.docId = document.getId();
		this.address = document.getString("address");
		this.amount = document.getString("amount");
		this.aptName = document.getString("aptName");
		this.bathrooms = (int)(long) document.get("bathrooms");
		this.capacity = (int)(long) document.get("capacity");
		this.duration = document.getString("duration");
		this.existingMembers = (int)(long) document.get("existingMembers");
		this.leaseType = document.getString("leaseType");
		this.note = document.getString("note");
		this.parking = document.getString("parking");
		this.petFriendly = document.getString("petFriendly");
		this.rooms = (int)(long) document.get("rooms");
	}
	
	public LeaseInfoModel(DocumentSnapshot document) {
		this.docId = document.getId();
		this.address = document.getString("address");
		this.amount = document.getString("amount");
		this.aptName = document.getString("aptName");
		this.bathrooms = (int)(long) document.get("bathrooms");
		this.capacity = (int)(long) document.get("capacity");
		this.duration = document.getString("duration");
		this.existingMembers = (int)(long) document.get("existingMembers");
		this.leaseType = document.getString("leaseType");
		this.note = document.getString("note");
		this.parking = document.getString("parking");
		this.petFriendly = document.getString("petFriendly");
		this.rooms = (int)(long) document.get("rooms");
	}
	
	public LeaseInfoModel() {}

	public String getDocId() {
		return docId;
	}
	public void setDocId(String docId) {
		this.docId = docId;
	}
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
	
	public static List<LeaseInfoModel> getLeaseList(List<QueryDocumentSnapshot> documents) {
		List<LeaseInfoModel> list = new ArrayList<>();
		for(QueryDocumentSnapshot document : documents) {
			list.add(new LeaseInfoModel(document));
		}
		
		return list;
	}
	
	@Override
	public String toString() {
		return "LeaseInfoModel [docID="+docId+", address=" + address + ", amount=" + amount + ", aptName=" + aptName + ", bathrooms=" + bathrooms + ", capacity=" + capacity + ", duration=" + duration
				+ ", existingMembers=" + existingMembers + ", leaseType=" + leaseType + ", note=" + note + ", parking="
				+ parking + ", petFriendly=" + petFriendly + ", rooms=" + rooms + "]";
	}
	
}
