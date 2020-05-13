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
		
		return "Wortschatz";
	}
	
	@PostMapping("/save")
	public String MakeUpdate(@RequestParam("id") int TheId, @RequestParam("status") int Status ) {
		
		// get the employee from the service
		Woerterbuch theWoerterbuch = woerterbuchService.findById(TheId);
		theWoerterbuch.setStatus(Status);
		woerterbuchService.save(theWoerterbuch);
		
		return "Wortschatz_empty";

	}

	@PostMapping("/delete")
	public String MakeDelete(@RequestParam("id") int theInt ) {
		
		woerterbuchService.deleteById(theInt);
		
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
				
		int Status = 1;
		theModel.addAttribute("Worte", therotWoerterbuch);
		theModel.addAttribute("messageStatus", Status);
		
		return "Wortschatz_rot_gelb_grun";
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
		
		int Status = 2;		
		theModel.addAttribute("Worte", thegelbWoerterbuch);
		theModel.addAttribute("messageStatus", Status);
		
		return "Wortschatz_rot_gelb_grun";
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
		
		int Status = 3;
		theModel.addAttribute("Worte", thegrunWoerterbuch);
		theModel.addAttribute("messageStatus",Status);
		
		return "Wortschatz_rot_gelb_grun";
	}
	
		
}
