	package com.fml.controller;

import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.fml.FmlApplication;
import com.fml.model.LeaseInfoModel;
import com.fml.service.AuthService;
import com.fml.service.FirestoreService;
import com.fml.service.LeaseService;

@Controller
public class LeaseController {

	@Autowired
	FirestoreService fireService;
	
	@Autowired
	AuthService authService;
	
	@Autowired
	LeaseService leaseService;
	
	@GetMapping("/lease")
	public String addLease(Model model) {
		LeaseInfoModel lease = new LeaseInfoModel();
		model.addAttribute("lease",lease);
		
		return "addLease";
	}
	
	@PostMapping("/success")
	public String ade(@ModelAttribute("lease") LeaseInfoModel lease) throws InterruptedException, ExecutionException {
		fireService.addObject((Object)lease, FmlApplication.Lease);
		
		return "redirect:/home";
	}
	
	@GetMapping("/home")
	public String viewLease(Model model, HttpServletRequest request) throws InterruptedException, ExecutionException {
		if(authService.getCookieValIfExists(FmlApplication.UserIdCookie, request)!=null) {
			List<LeaseInfoModel> list = LeaseInfoModel.getLeaseList(fireService.getAllDocuments(FmlApplication.Lease));
			model.addAttribute("leaseList",list);
			
			return "home";
		}
		
		return "redirect:/login";
	}
	
	//redirect to same page pass paginate size, and document as params
	
	@GetMapping("/lease/view/{leaseId}")
	public String viewLeaseFull(@PathVariable String leaseId, Model model, HttpServletRequest request) throws InterruptedException, ExecutionException {
		String cookieVal = authService.getCookieValIfExists(FmlApplication.UserIdCookie, request);
		if(cookieVal!=null) {
			model.addAttribute("lease",new LeaseInfoModel(fireService.getDocument(FmlApplication.Lease, leaseId)));
			model.addAttribute("isWishlist", leaseService.isWishlist(leaseId, cookieVal, fireService));
			
			return "viewLease";
		}
		
		return "redirect:/login";
	}
	
	@GetMapping("/lease/wishlist/{type}/{leaseId}")
	public String addToWishlist(@PathVariable String leaseId, @PathVariable String type, Model model, HttpServletRequest request) throws InterruptedException, ExecutionException {
		System.out.println("WISHLIST: \n"+type+"\t"+leaseId);
		leaseService.handleWishlist(authService.getCookieValIfExists(FmlApplication.UserIdCookie, request),
				type, fireService, leaseId);
		
		return "redirect:/lease/view/"+leaseId;
	}
	
	@GetMapping("/wishlist")
	public String viewWishlist(Model model, HttpServletRequest request) throws InterruptedException, ExecutionException {
		System.out.println("wishlist controller");
		String cookieVal = authService.getCookieValIfExists(FmlApplication.UserIdCookie, request);
		if(cookieVal!=null) {
			List<LeaseInfoModel> wishlist = leaseService.getWishlist(fireService, cookieVal);
			model.addAttribute("leaseList", wishlist);
			if(wishlist==null) {
				model.addAttribute("emptyList",true);
			}
			
			return "wishlist";
		}
		
		return "redirect:/login";
	}

}
