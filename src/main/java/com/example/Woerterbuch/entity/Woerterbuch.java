package com.example.Woerterbuch.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name="woerterbuch")
public class Woerterbuch {
	
	// define fields
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="wort_de")
	private String Wort_DE;	
	
	@Column(name="wort_sk")
	private String Wort_SK;
	
	@Column(name="status")
	private int Status;
	
	@ManyToOne(cascade= {CascadeType.DETACH,CascadeType.MERGE,
			CascadeType.PERSIST, CascadeType.REFRESH} )
	@JoinColumn(name="username")
	private Users User;
	

	public void setUser(Users user) {
		User = user;
	}


	public Woerterbuch() {
			
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getWort_DE() {
		return Wort_DE;
	}

	public void setWort_DE(String wort_DE) {
		Wort_DE = wort_DE;
	}

	public String getWort_SK() {
		return Wort_SK;
	}

	public void setWort_SK(String wort_SK) {
		Wort_SK = wort_SK;
	}

	public int getStatus() {
		return Status;
	}

	public void setStatus(int status) {
		Status = status;
	}

	
}
