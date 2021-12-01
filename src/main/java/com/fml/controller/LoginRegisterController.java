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
import com.fml.service.CryptService;
import com.fml.service.FirestoreService;

@Controller
public class LoginRegisterController {

	@Autowired
	FirestoreService fireService;
	
	@Autowired
	CryptService cryptService;
	
	@GetMapping("/")
	public String loginRedirect() {
		
		return "redirect:/login";
	}

	@GetMapping("/login")
	public String login(Model model) {
		model.addAttribute("loginObj", new LoginModel());
		
		return "login";
	}
	
	@PostMapping("/login")
	public String auth(@ModelAttribute("loginObj") LoginModel loginObj, Model model) {
		System.out.println(loginObj);
		
		return "redirect:/home";
	}
	
}
