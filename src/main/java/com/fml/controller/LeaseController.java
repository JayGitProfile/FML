package com.fml.controller;

import java.util.List;
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
		fireService.addObject((Object)lease, "LeaseInfo");
		
		return "redirect:/home";
	}
	
	@GetMapping("/home")
	public String viewLease(Model model) throws InterruptedException, ExecutionException {
		System.out.println("controlla");
		List<LeaseInfoModel> list = LeaseInfoModel.getLeaseList(fireService.getAllDocuments("LeaseInfo"));
		model.addAttribute("leaseList",list);
			
		return "home";
	}
	
	//redirect to same page pass paginate size, and document as params
	
}
