package com.fml.controller;

import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.fml.model.LeaseInfoModel;
import com.fml.service.FirestoreService;

@Controller
public class LeaseController {

	@Autowired
	FirestoreService fireService;
	
	@GetMapping("/lease")
	public String addLease(Model model) {
		LeaseInfoModel lease = new LeaseInfoModel();
		model.addAttribute("lease",lease);
		
		return "addLease";
	}
	
	@PostMapping("/success")
	public String ade(@ModelAttribute("lease") LeaseInfoModel lease) throws InterruptedException, ExecutionException {
		System.out.println(lease);
		fireService.addObject((Object)lease, "LeaseInfo");
		
		return "redirect:/lease";
	}
	
	//redirect to same page pass paginate size, and document as params
	
}
