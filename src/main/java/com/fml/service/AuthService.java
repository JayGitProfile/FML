package com.fml.service;

import java.util.concurrent.ExecutionException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.fml.model.LoginModel;
import com.fml.model.UserProfileModel;
import com.google.cloud.firestore.DocumentSnapshot;

@Service
public class AuthService {

	public void addCookie(String key, String val, HttpServletResponse response) {
		Cookie cookie = new Cookie(key, val);
	    response.addCookie(cookie);
	}

	public boolean isAuthUser(LoginModel loginObj, FirestoreService fireService, HttpServletResponse response, CryptService cryptService) throws InterruptedException, ExecutionException {
		System.out.println("auth begin");
		DocumentSnapshot doc = fireService.getDocument("FmlUserProfiles", loginObj.getEmail());
		if(doc.exists() && (loginObj.getPswd().equals(cryptService.decrypt(doc.getString("pswd"))))) { // is valid user
			System.out.println("is valid bruh enjoy");
			addCookie("fmlUname", loginObj.getEmail(), response);
			
			return true;
		}
		System.out.println("auth failed");
		//addUserCookie(loginObj.getEmail());
		
		return false;
	}
	
	public String getCookieValIfExists(String cookieKey, HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
	    if (cookies != null) {
	        for(Cookie c: cookies) {
	        	if(c.getName().equals(cookieKey)) {
	        		System.out.println("exists\t"+c.getName() + "=" + c.getValue());
	        		
	        		return c.getValue();
	        	}
	        }
	    }
	    System.out.println("cookie "+cookieKey+" not found");
	    
		return null;
	}
	
	/*public boolean isValidCookie(String cookieKey, String cookieValue) {
		Cookie[] cookies = request.getCookies();
	    if (cookies != null) {
	        for(Cookie c: cookies) {
	        	//if(c.getName().equals(cookieKey)) {
	        		System.out.println(c.getName() + "=" + c.getValue());
	        		
	        	//	return true;
	        	//}
	        }
	    }
	    
		return false;
	}*/
}
