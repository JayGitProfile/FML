package com.fml.service;

import java.util.concurrent.ExecutionException;

import org.springframework.stereotype.Service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;

@Service
public class FirestoreService {
	
	public boolean addObject(Object obj, String collectionId, String docId) throws InterruptedException, ExecutionException {
		Firestore dbf = FirestoreClient.getFirestore();
		ApiFuture<WriteResult> write = dbf.collection(collectionId).document(docId).set(obj);
		
		return write.isDone();
	}
	
	public boolean addObject(Object obj, String collectionId) throws InterruptedException, ExecutionException {
		Firestore dbf = FirestoreClient.getFirestore();
		String docId = dbf.collection(collectionId).document().getId();
		System.out.println("ID "+docId);
		
		ApiFuture<WriteResult> write = dbf.collection(collectionId).document(docId).set(obj);
		
		return write.isDone();
	}
}
