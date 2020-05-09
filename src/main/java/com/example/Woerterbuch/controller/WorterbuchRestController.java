package com.example.Woerterbuch.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Woerterbuch.entity.Woerterbuch;
import com.example.Woerterbuch.service.WoerterbuchService;

@RestController
@RequestMapping("/Woerterbuch")
public class WorterbuchRestController {

	
private WoerterbuchService woerterbuchService;
	
	public WorterbuchRestController(WoerterbuchService thewoerterbuchService) {
	this.woerterbuchService = thewoerterbuchService;
}
	
	@PostMapping("/add")
	public Woerterbuch addWort(@RequestBody Woerterbuch theWoerterbuch) {
		
		// also just case they pass an id in JSON ... set id to 0
		// this is to force a save of new item ... instead of update
		
		theWoerterbuch.setId(0);
		
		woerterbuchService.save(theWoerterbuch);
		
		return theWoerterbuch;			
		
	}
	
}