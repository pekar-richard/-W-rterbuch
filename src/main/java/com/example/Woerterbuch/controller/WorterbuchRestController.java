package com.example.Woerterbuch.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Woerterbuch.entity.Users;
import com.example.Woerterbuch.entity.Woerterbuch;
import com.example.Woerterbuch.service.UsersService;
import com.example.Woerterbuch.service.WoerterbuchService;

@RestController
@RequestMapping("/Woerterbuch")
public class WorterbuchRestController {

	
@Autowired
private WoerterbuchService woerterbuchService;

@Autowired
private UsersService usersService;
		
	public WorterbuchRestController(WoerterbuchService thewoerterbuchService,UsersService theusersService) {
		
	this.woerterbuchService = thewoerterbuchService;
	this.usersService = theusersService;
	}
	
	@PostMapping("/add")
	public Woerterbuch addWort(@RequestBody Woerterbuch theWoerterbuch, Principal principal) {
				
		theWoerterbuch.setId(0);
	
		String username = principal.getName();
		
		Users theuser = usersService.findUserByName(username);
	
		theuser.add(theWoerterbuch);

		woerterbuchService.save(theWoerterbuch);	
			
		return theWoerterbuch;
		
	}
	
	@PostMapping("/search")
	public List<Woerterbuch> findWortsByWort_DEandStatus(@RequestBody Woerterbuch theWoerterbuch, Principal principal ) {
		
		String username = principal.getName();
		
		return woerterbuchService.ByStatusAndWort_DE(theWoerterbuch.getStatus(),theWoerterbuch.getWort_DE(), username);
	    
	    }
	
	@PostMapping("/searchalle")
	public List<Woerterbuch> findWortsByWort_DE(@RequestBody Woerterbuch theWoerterbuch, Principal principal ) {
		
		String username = principal.getName();
		
	    return woerterbuchService.findByWort_DE(theWoerterbuch.getWort_DE(),username );
	
	    }
}