package com.example.Woerterbuch.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.Woerterbuch.entity.Woerterbuch;
import com.example.Woerterbuch.service.WoerterbuchService;

@Controller
@RequestMapping("/Woerterbuch")
public class WoerterbuchController {
	
private WoerterbuchService woerterbuchService;
	
	public WoerterbuchController(WoerterbuchService thewoerterbuchService) {
	this.woerterbuchService = thewoerterbuchService;
}

	// add mapping for "/list"
	
	@GetMapping("/List")
	public String listWortschatz(Model theModel) {
		
		List<Woerterbuch> theWoerterbuch = woerterbuchService.findAll();
		// add to the spring model
		theModel.addAttribute("Worte", theWoerterbuch);
		
		return "Wortschatz/Wortschatz";
	}
	
	@PostMapping("/save")
	public String MakeUpdate(@RequestParam("id") int TheId, @RequestParam("status") int Status ) {
		
		// get the employee from the service
		Woerterbuch theWoerterbuch = woerterbuchService.findById(TheId);
		theWoerterbuch.setStatus(Status);
		woerterbuchService.save(theWoerterbuch);
		
		return "Wortschatz/Wortschatz_empty";

	}
	
	@PostMapping("/add")
	public String MakeAdd(@RequestParam("Wort_DE") String theWort_DE, @RequestParam("Wort_SK") String theWort_SK ) {
		
		// get the employee from the service
		Woerterbuch theWoerterbuch = new Woerterbuch();
		theWoerterbuch.setWort_DE(theWort_DE);
		theWoerterbuch.setWort_SK(theWort_SK);
		theWoerterbuch.setStatus(1);
		woerterbuchService.save(theWoerterbuch);
		
		return "redirect:/Woerterbuch/List";
	}
	
	
	@GetMapping("/rot")
	public String listrotWortschatz(Model theModel) {
		
		List<Woerterbuch> theWoerterbuch = woerterbuchService.findAll();
		// add to the spring model
		
		List<Woerterbuch> therotWoerterbuch = new ArrayList<>();
		
		for (Woerterbuch temp : theWoerterbuch) {
			
			if(temp.getStatus() == 1) {
				therotWoerterbuch.add(temp);
				
			}
		}		
				
		theModel.addAttribute("Worte", therotWoerterbuch);
		
		return "Wortschatz/Wortschatz_rot_gelb_grun";
	}
	
	@GetMapping("/gelb")
	public String listgelbWortschatz(Model theModel) {
		
		List<Woerterbuch> theWoerterbuch = woerterbuchService.findAll();
		// add to the spring model
		
		List<Woerterbuch> thegelbWoerterbuch = new ArrayList<>();
		
		for (Woerterbuch temp : theWoerterbuch) {
			
			if(temp.getStatus() == 2) {
				thegelbWoerterbuch.add(temp);
				
			}
		}		
				
		theModel.addAttribute("Worte", thegelbWoerterbuch);
		
		return "Wortschatz/Wortschatz_rot_gelb_grun";
	}
	
	@GetMapping("/grun")
	public String listgrunWortschatz(Model theModel) {
		
		List<Woerterbuch> theWoerterbuch = woerterbuchService.findAll();
		// add to the spring model
		
		List<Woerterbuch> thegrunWoerterbuch = new ArrayList<>();
		
		for (Woerterbuch temp : theWoerterbuch) {
			
			if(temp.getStatus() == 3) {
				thegrunWoerterbuch.add(temp);
				
			}
		}		
				
		theModel.addAttribute("Worte", thegrunWoerterbuch);
		
		return "Wortschatz/Wortschatz_rot_gelb_grun";
	}
	
	
}
