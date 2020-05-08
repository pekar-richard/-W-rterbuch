package com.example.Woerterbuch.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Woerterbuch.entity.Woerterbuch;

public interface WoerterbuchRepository extends JpaRepository<Woerterbuch, Integer> {
	

}
