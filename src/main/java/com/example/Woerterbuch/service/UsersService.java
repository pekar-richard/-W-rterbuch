package com.example.Woerterbuch.service;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.example.Woerterbuch.entity.Users;
import com.example.Woerterbuch.entity.Woerterbuch;

public interface UsersService {
	
	public Users findUserByName(String username);
			
}
