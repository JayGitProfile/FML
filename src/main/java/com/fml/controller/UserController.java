package com.fml.controller;

import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.fml.model.UserProfileModel;
import com.fml.service.FirestoreService;

@Controller
public class UserController {

	@Autowired
	FirestoreService fireService;
	
	@GetMapping("/register")
	public String register(Model model) {
		model.addAttribute("profile", new UserProfileModel());
		
		return "register";
	}
	
	@PostMapping("/register")
	public String addUserProfile(@ModelAttribute("profile") UserProfileModel profile, Model model) throws InterruptedException, ExecutionException {		
		if(!fireService.ifDocExists("FmlUserProfiles", profile.getEmail())) {
			fireService.addObject((Object)profile, "FmlUserProfiles", profile.getEmail());
		}
		else {
			return "redirect:/reregister";
		}
		
		return "redirect:/login";
	}
	
	@GetMapping("/reregister")
	public String reRegister(Model model) {
		UserProfileModel profile = new UserProfileModel();
		model.addAttribute("profile",profile);
		model.addAttribute("errorMessage","Profile Already Exists! Try Again.");
		
		return "register"; 
	}
	
	@GetMapping("/profile/{type}")
	public String showProfile(@PathVariable String type, Model model) {
		System.out.println("type: "+type);
		UserProfileModel profile = new UserProfileModel();
		profile.setfName("J");
		profile.setlName("S");
		
		model.addAttribute("profile", profile);
		model.addAttribute("type",type);
		
		return "profile";
	}
	
	@PostMapping("/profile/update")
	public String updateUserProfile(@ModelAttribute("profile") UserProfileModel profile, Model model) {		
		System.out.println(profile);
				
		return "redirect:/profile/view";
	}
}
