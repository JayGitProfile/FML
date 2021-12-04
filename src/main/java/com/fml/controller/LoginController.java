package com.fml.controller;

import java.util.concurrent.ExecutionException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fml.FmlApplication;
import com.fml.model.LoginModel;
import com.fml.model.UserProfileModel;
import com.fml.service.AuthService;
import com.fml.service.CryptService;
import com.fml.service.FirestoreService;

@Controller
public class LoginController {

	@Autowired
	FirestoreService fireService;
	
	@Autowired
	CryptService cryptService;
	
	@Autowired
	AuthService authService;
		
	@GetMapping({"/","/login"})
	public String login(Model model, HttpServletRequest request, @RequestParam(required=false,name="error") String error,
			@RequestParam(required=false,name="register") String register) { // read cookie, ifExists redirect to home
		model.addAttribute("loginObj", new LoginModel());
		if(error!=null && error.equals("fail")) {
			model.addAttribute("error","Incorrect Email or password");
		}
		if(register!=null && register.equals("OK")) {
			model.addAttribute("error","Registered successfully! Login to continue.");
		}
		String cookieVal = authService.getCookieValIfExists(FmlApplication.UserIdCookie, request);
		if(cookieVal!=null) { 
			
			return "redirect:/home";
		}
		
		return "login";
	}
	
	@PostMapping("/login")
	public String auth(@ModelAttribute("loginObj") LoginModel loginObj, Model model, HttpServletResponse response) 
			throws InterruptedException, ExecutionException { 
		if(authService.isAuthUser(loginObj, fireService, response, cryptService)) {
			return "redirect:/home";
		}
		
		return "redirect:/login?error=fail";
	}
	
	@GetMapping("/logout")
	public String logout(HttpServletResponse response) {
		Cookie cookie = new Cookie(FmlApplication.UserIdCookie, null);
		cookie.setMaxAge(0);
		cookie.setSecure(true);
		cookie.setHttpOnly(true);
		cookie.setPath("/");
		response.addCookie(cookie);
		
		return "redirect:/login";
	}
	
}
