package com.example.Woerterbuch.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.Woerterbuch.entity.Users;
import com.example.Woerterbuch.entity.Woerterbuch;
import com.example.Woerterbuch.service.UsersService;
import com.example.Woerterbuch.service.WoerterbuchService;

@Controller
@RequestMapping("/Woerterbuch")
public class WoerterbuchController {

@Autowired
private WoerterbuchService woerterbuchService;

@Autowired
private UsersService usersService;
	
	public WoerterbuchController(WoerterbuchService thewoerterbuchService,UsersService theusersService) {
	this.woerterbuchService = thewoerterbuchService;
	this.usersService = theusersService;
	}
	

	// add mapping for "/list"
	
	@GetMapping("/List")
	public String listWortschatz(Model theModel,  Principal principal) {
			
		String username = principal.getName();
			
		List<Woerterbuch> theWoerterbuch = woerterbuchService.findAllByName(username);		

		// add to the spring model
		theModel.addAttribute("Worte", theWoerterbuch);
		
		return "Wortschatz";
	}
	
	@PostMapping("/save")
	public String MakeUpdate(@RequestParam("id") int TheId, @RequestParam("status") int Status,  Principal principal ) {
		
		Woerterbuch theWoerterbuch = woerterbuchService.findById(TheId);
		theWoerterbuch.setStatus(Status);
		
		String username = principal.getName();
		
		Users theuser = usersService.findUserByName(username);
		
		theuser.add(theWoerterbuch);
		
		woerterbuchService.save(theWoerterbuch);
		
		return "Wortschatz_empty";

	}

	@PostMapping("/delete")
	public String MakeDelete(@RequestParam("id") int theInt ) {
		
		woerterbuchService.deleteById(theInt);
		
		return "redirect:/Woerterbuch/List";
	}
	
	
	@GetMapping("/rot")
	public String listrotWortschatz(Model theModel, Principal principal) {
		
		String username = principal.getName();
		
		List<Woerterbuch> theWoerterbuch = woerterbuchService.findAllByName(username);	
		// add to the spring model
		
		List<Woerterbuch> therotWoerterbuch = new ArrayList<>();
		
		for (Woerterbuch temp : theWoerterbuch) {

			if(temp.getStatus() == 1 ) {
				therotWoerterbuch.add(temp);
				
			}
		}		
				
		int Status = 1;
		theModel.addAttribute("Worte", therotWoerterbuch);
		theModel.addAttribute("messageStatus", Status);
		
		return "Wortschatz_rot_gelb_grun";
	}
	
	@GetMapping("/gelb")
	public String listgelbWortschatz(Model theModel, Principal principal) {
		
		String username = principal.getName();
		
		List<Woerterbuch> theWoerterbuch = woerterbuchService.findAllByName(username);	
		// add to the spring model
		
		List<Woerterbuch> thegelbWoerterbuch = new ArrayList<>();
		
		for (Woerterbuch temp : theWoerterbuch) {
			
			if(temp.getStatus() == 2) {
				thegelbWoerterbuch.add(temp);
				
			}
		}	
		
		int Status = 2;		
		theModel.addAttribute("Worte", thegelbWoerterbuch);
		theModel.addAttribute("messageStatus", Status);
		
		return "Wortschatz_rot_gelb_grun";
	}
	
	@GetMapping("/grun")
	public String listgrunWortschatz(Model theModel, Principal principal) {
		
		String username = principal.getName();
		
		List<Woerterbuch> theWoerterbuch = woerterbuchService.findAllByName(username);
		
		List<Woerterbuch> thegrunWoerterbuch = new ArrayList<>();
		
		for (Woerterbuch temp : theWoerterbuch) {
			
			if(temp.getStatus() == 3) {
				thegrunWoerterbuch.add(temp);
				
			}
		}		
		
		int Status = 3;
		theModel.addAttribute("Worte", thegrunWoerterbuch);
		theModel.addAttribute("messageStatus",Status);
		
		return "Wortschatz_rot_gelb_grun";
	}
	
		
}
