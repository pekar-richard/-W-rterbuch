package com.example.Woerterbuch.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.Woerterbuch.entity.Users;


public interface UsersRepository extends JpaRepository<Users, String> {


	@Query("from Users where username=:Username")  
	public Users findUserByName(@Param("Username") String u);
	
	
}