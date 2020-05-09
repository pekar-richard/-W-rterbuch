package com.example.Woerterbuch.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Woerterbuch.dao.WoerterbuchRepository;
import com.example.Woerterbuch.entity.Woerterbuch;

@Service
public class WoerterbuchServiceImpl implements WoerterbuchService {
	
	private WoerterbuchRepository woerterbuchRepository;
	
	@Autowired
	public WoerterbuchServiceImpl(WoerterbuchRepository theWoerterbuchRepository) {	
		woerterbuchRepository=theWoerterbuchRepository;		
	}

	@Override
	public List<Woerterbuch> findAll() {

		return woerterbuchRepository.findAllByOrderByIdDesc();
	}
	

	@Override
	public Woerterbuch findById(int theId) {

		Optional<Woerterbuch> result = woerterbuchRepository.findById(theId);
		
		Woerterbuch theWoerterbuch = null;
		
		if(result.isPresent()) {
			
			theWoerterbuch = result.get();	
		}
		else {
			
			// we didn't find the employee
			throw new RuntimeException("Did not find Woerterbuch id - " + theId);	
		}
	
		return theWoerterbuch;
	}

	@Override
	public void save(Woerterbuch theWoerterbuch) {

		woerterbuchRepository.save(theWoerterbuch);
	}

	@Override
	public void deleteById(int theId) {

		woerterbuchRepository.deleteById(theId);
	}

}
