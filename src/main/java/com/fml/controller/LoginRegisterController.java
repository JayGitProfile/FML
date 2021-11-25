package com.fml.controller;

import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fml.model.LoginModel;
import com.fml.model.UserProfileModel;
import com.fml.service.FirestoreService;

@Controller
public class LoginRegisterController {

	@Autowired
	FirestoreService fireService;
	
	@GetMapping("/login")
	public String login(Model model) {
		model.addAttribute("loginObj", new LoginModel());
		
		return "logintemp";
	}
	
	@PostMapping("/login")
	public String auth(@ModelAttribute("loginObj") LoginModel loginObj, Model model) {
		System.out.println(loginObj);
		
		return "redirect:/home";
	}
	
	@GetMapping("/register")
	public String register(Model model) {
		model.addAttribute("profile", new UserProfileModel());
		
		return "registertemp";
	}
	
	@PostMapping("/register")
	public String addUserProfile(@ModelAttribute("profile") UserProfileModel profile, Model model) throws InterruptedException, ExecutionException {		
		if(!fireService.ifDocExists("FmlUserProfiles", profile.getEmail())) {
			fireService.addObject((Object)profile, "FmlUserProfiles", profile.getEmail());
		}
		else {
			return "redirect:/register/error";
		}
		
		return "redirect:/login";
	}
	
	@GetMapping("/register/{error}")
	public String register(@PathVariable String error, Model model) {
		UserProfileModel profile = new UserProfileModel();
		model.addAttribute("profile",profile);
		model.addAttribute("errorMessage","Profile Already Exists! Try Again.");
		
		return "register"; 
	}
	
	@GetMapping("/")
	public String temp() {
		
		return "logintemp";
	}
}
