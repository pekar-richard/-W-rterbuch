package com.example.Woerterbuch.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="woerterbuch")
public class Woerterbuch {
	
	// define fields
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="Wort_DE")
	private String Wort_DE;	
	
	@Column(name="Wort_SK")
	private String Wort_SK;
	
	@Column(name="Status")
	private int Status;
	
	public Woerterbuch() {
			
	}

	public Woerterbuch(int id, String wort_DE, String wort_SK, int status) {
		this.id = id;
		Wort_DE = wort_DE;
		Wort_SK = wort_SK;
		Status = status;
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

	@Override
	public String toString() {
		return "Woerterbuch [id=" + id + ", Wort_DE=" + Wort_DE + ", Wort_SK=" + Wort_SK + ", Status=" + Status + "]";
	}
	
	
}
