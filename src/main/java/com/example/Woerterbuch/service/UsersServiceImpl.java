package com.example.Woerterbuch.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Woerterbuch.dao.UsersRepository;
import com.example.Woerterbuch.entity.Users;


@Service
public class UsersServiceImpl implements UsersService {
	
	private UsersRepository usersRepository;
	
	@Autowired
	public UsersServiceImpl(UsersRepository theusersRepository) {	
		usersRepository=theusersRepository;		
	}


	@Override
	public Users findUserByName(String username) {
		
		return usersRepository.findUserByName(username);
	}


}
