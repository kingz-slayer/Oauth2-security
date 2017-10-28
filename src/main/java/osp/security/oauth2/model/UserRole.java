package osp.security.oauth2.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class UserRole {
	
	@JsonIgnore
	@Id
	@GeneratedValue
	private Long id;
	
	@JsonIgnore
	@ManyToOne
	private User userParent;
	
	
	@ManyToOne
	private Roles userRole;

	
	UserRole(){
		
	}


	public UserRole(User userParent, Roles userRole) {
		super();
		this.userParent = userParent;
		this.userRole = userRole;
	}


	public Long getId() {
		return id;
	}


	public User getUserParent() {
		return userParent;
	}


	public Roles getUserRole() {
		return userRole;
	}
	
	
	
	
	
	
	
	
}
