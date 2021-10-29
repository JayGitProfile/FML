package com.fml.controller;

import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.fml.model.UserProfileModel;
import com.fml.service.FirestoreService;

@Controller
public class LoginRegisterController {

	@Autowired
	FirestoreService fireService;
	
	@GetMapping("/register")
	public String register(Model model) {
		UserProfileModel profile = new UserProfileModel();
		model.addAttribute("profile",profile);
		
		return "register";
	}
	
	@PostMapping("/home")
	public String addUserProfile(@ModelAttribute("profile") UserProfileModel profile) throws InterruptedException, ExecutionException {
		fireService.addObject((Object)profile, "FmlUserProfiles", profile.getEmail());		
		
		return "login";
	}
}
