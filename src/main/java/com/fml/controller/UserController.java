package com.fml.controller;

import java.util.concurrent.ExecutionException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fml.FmlApplication;
import com.fml.model.UserProfileModel;
import com.fml.service.AuthService;
import com.fml.service.CryptService;
import com.fml.service.FirestoreService;

@Controller
public class UserController {

	@Autowired
	FirestoreService fireService;
	
	@Autowired
	AuthService authService;
	
	@Autowired
	CryptService cryptService;
	
	@GetMapping("/register")
	public String register(Model model, @RequestParam(required=false,name="error") String error) {
		model.addAttribute("profile", new UserProfileModel());
		if(error!=null && error.equals("exist")) {
			model.addAttribute("error","Profile Already Exists! Try Again with different email.");
		}	
		
		return "register";
	}
	
	@PostMapping("/register")
	public String addUserProfile(@ModelAttribute("profile") UserProfileModel profile, Model model) throws InterruptedException, ExecutionException {		
		if(!fireService.getDocument(FmlApplication.UserProfile, profile.getEmail()).exists()) {
			profile.setPswd(cryptService.encrypt(profile.getPswd()));
			fireService.addObject((Object)profile, FmlApplication.UserProfile, profile.getEmail());
		}
		else {
			return "redirect:/register?error=exist";
		}
		
		return "redirect:/login?register=OK";
	}
	
	@GetMapping({"/profile","/profile/{type}"})
	public String showProfile(@PathVariable(required=false) String type, Model model, HttpServletRequest request) throws InterruptedException, ExecutionException {
		String cookieVal = authService.getCookieValIfExists("fmlUname", request);
		if(cookieVal!=null) {
			model.addAttribute("profile", new UserProfileModel(fireService.getDocument("FmlUserProfiles", cookieVal)));
			model.addAttribute("type",type);
			
			return "profile";
		}
				
		return "redirect:/login";
	}
	
	@PostMapping("/profile/update")
	public String updateUserProfile(@ModelAttribute("profile") UserProfileModel profile, Model model, HttpServletRequest request) throws InterruptedException, ExecutionException {		
		String cookieVal = authService.getCookieValIfExists("fmlUname", request);
		if(cookieVal!=null) {
			profile.setEmail(cookieVal);
			fireService.addObject((Object)profile, "FmlUserProfiles", profile.getEmail());
		}
		
		return "redirect:/profile/view";
	}
}
