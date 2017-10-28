package osp.security.oauth2.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class UserAccount {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column
	private String userName;
	
	@Column
	@JsonIgnore
	private String password;
	
	@JsonIgnore
	@OneToOne(mappedBy ="userAccount")
	private User user;

	UserAccount(){
		
	}
	
	public UserAccount(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}

	public User getUser() {
		return user;
	}
	
	
	
}
