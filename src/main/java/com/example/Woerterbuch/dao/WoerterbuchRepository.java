package com.example.Woerterbuch.dao;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Woerterbuch.entity.Woerterbuch;


public interface WoerterbuchRepository extends JpaRepository<Woerterbuch, Integer> {

	public List<Woerterbuch> findAllByOrderByIdDesc();
	
}
