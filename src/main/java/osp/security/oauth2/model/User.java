package osp.security.oauth2.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class User {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name="FIRST_NAME")
	private String firstName;
	
	@Column(name="LAST_NAME")
	private String lastName;
	
	@Column(name="EMAIL_ID")
	private String emailAddress;
	
	@OneToMany(mappedBy ="userParent")
	private Set<UserRole> userRoles = new HashSet<>();
	
	@JsonIgnore
	@OneToOne
	private UserAccount userAccount;

	
	public User(String firstName, String lastName, String emailAddress, UserAccount userAccount){
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailAddress = emailAddress;
		this.userAccount = userAccount;
	}
	
	User(){
		
	}
	
	public UserAccount getUser() {
		return userAccount;
	}

	public Long getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public Set<UserRole> getUserRoles() {
		return userRoles;
	}
	
	
}
