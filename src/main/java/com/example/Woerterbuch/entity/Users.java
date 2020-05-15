package com.example.Woerterbuch.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="users")
public class Users {
	
		@Id
		@Column(name="username")
		private String Username;			
				
		@OneToMany(fetch=FetchType.LAZY,
				mappedBy = "User",
				cascade= {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST, CascadeType.REFRESH})	
		private List<Woerterbuch> Woerterbuchs;
		
		public void add(Woerterbuch tempWoerterbuch) {
			
			if(Woerterbuchs == null) {
				
				Woerterbuchs = new ArrayList<>();
			}
			
			Woerterbuchs.add(tempWoerterbuch);
			
			tempWoerterbuch.setUser(this);;
		}
		
		
		public Users() {
				
		}


		public String getUsername() {
			return Username;
		}


		public void setUsername(String username) {
			Username = username;
		}


		public List<Woerterbuch> getWoerterbuchs() {
			return Woerterbuchs;
		}


		public void setWoerterbuchs(List<Woerterbuch> woerterbuchs) {
			Woerterbuchs = woerterbuchs;
		}

		
}
