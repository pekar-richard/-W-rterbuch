package com.example.Woerterbuch.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.Woerterbuch.entity.Woerterbuch;


public interface WoerterbuchRepository extends JpaRepository<Woerterbuch, Integer> {


	public List<Woerterbuch> findAllByOrderByIdDesc();
	
	@Query("from Woerterbuch where status=:Status")  
	List<Woerterbuch> findByStatus(@Param("Status")int s);
	
	@Query("from Woerterbuch where wort_de LIKE CONCAT('%',:wort_de,'%')")
	List<Woerterbuch> findByWort_DE(@Param("wort_de")String w);
	
	@Query("from Woerterbuch where status=:status and wort_de LIKE CONCAT('%',:wort_de,'%')")
	List<Woerterbuch> ByStatusAndWort_DE(@Param("status") int s, @Param("wort_de") String w);
	
	
}