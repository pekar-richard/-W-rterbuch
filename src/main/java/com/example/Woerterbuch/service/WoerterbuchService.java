package com.example.Woerterbuch.service;

import java.util.List;

import com.example.Woerterbuch.entity.Woerterbuch;

public interface WoerterbuchService {
	
	public List<Woerterbuch> findAll();
	
	public Woerterbuch findById(int theId);
	
	public void save(Woerterbuch theWoerterbuch);
	
	public void deleteById(int theId);
	
	public List<Woerterbuch> ByStatusAndWort_DE(int Status, String Wort_DE, String username );
	
	public List<Woerterbuch> findByWort_DE(String wort_de, String username );
	
	public List<Woerterbuch> findByStatus(int status);
	
	public List<Woerterbuch> findAllByName(String username);
	
			
}
