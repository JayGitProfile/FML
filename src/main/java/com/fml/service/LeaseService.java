package com.fml.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.stereotype.Service;

import com.fml.FmlApplication;
import com.fml.model.LeaseInfoModel;
import com.fml.model.WishlistModel;
import com.google.cloud.firestore.DocumentSnapshot;

@Service
public class LeaseService {
	
	public void handleWishlist(String cookieVal, String type, FirestoreService fireService, String leaseId) throws InterruptedException, ExecutionException {
		if(cookieVal!=null) {
			WishlistModel wishlist = null;
			if(type.equals("add")) {
				wishlist = addLeaseToWishlist(cookieVal, fireService, leaseId);
			}
			else if(type.equals("remove")) {
				wishlist = removeFromWishlist(cookieVal, fireService, leaseId);
			}
			
			fireService.addObject((Object)wishlist, FmlApplication.Wishlist, cookieVal);
		}	
	}
	
	public WishlistModel addLeaseToWishlist(String cookieVal, FirestoreService fireService, String leaseId) throws InterruptedException, ExecutionException {
		DocumentSnapshot doc = fireService.getDocument(FmlApplication.Wishlist, cookieVal);
		WishlistModel wishlist;
		if(doc.exists()) {
			wishlist = WishlistModel.createWishlistAdd(doc, leaseId);
		}
		else {
			wishlist = WishlistModel.createWishlistAdd(leaseId);
		}
		
		return wishlist;
	}

	public WishlistModel removeFromWishlist(String cookieVal, FirestoreService fireService, String leaseId) throws InterruptedException, ExecutionException {
		DocumentSnapshot doc = fireService.getDocument(FmlApplication.Wishlist, cookieVal);
		WishlistModel wishlist = null;
		if(doc.exists()) {
			wishlist = WishlistModel.createWishlistRemove(doc, leaseId);	
		}
		
		return wishlist;
	}
	
	public boolean isWishlist(String leaseId, String cookieVal, FirestoreService fireService) throws InterruptedException, ExecutionException {
		DocumentSnapshot doc = fireService.getDocument(FmlApplication.Wishlist, cookieVal);
		if(doc.exists()) {
			List<String> wishlist = (List<String>) doc.get("wishlist");
			if(wishlist.contains(leaseId)) {
				return true;
			}
		}
		
		return false;
	}
	
	public List<LeaseInfoModel> getWishlist(FirestoreService fireService, String cookieVal) throws InterruptedException, ExecutionException {
		DocumentSnapshot doc = fireService.getDocument(FmlApplication.Wishlist, cookieVal);
		if(doc.exists()) {
			List<String> wishLeaseIdList = (List<String>) doc.get("wishlist");
			List<LeaseInfoModel> wishlist = new ArrayList<>(); 
			for(String leaseId: wishLeaseIdList) {
				wishlist.add(new LeaseInfoModel(fireService.getDocument(FmlApplication.Lease, leaseId)));
			}
			if(!wishlist.isEmpty())
				return wishlist;
		}
		
		return null;
	}
}
