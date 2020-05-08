package com.example.Woerterbuch.service;

import java.util.List;

import com.example.Woerterbuch.entity.Woerterbuch;

public interface WoerterbuchService {
	
	public List<Woerterbuch> findAll();
	
	public Woerterbuch findById(int theId);
	
	public void save(Woerterbuch theWoerterbuch);
	
	public void deleteById(int theId);

}
