package osp.security.oauth2.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Roles {
	
	@JsonIgnore
	@OneToMany(mappedBy ="userRole")
	private Set<UserRole> userRoles = new HashSet<>();
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name ="ROLE_ID")
	private String roleId;
	
	@Column(name="ROLE_DESCRIPTION")
	private String roleDescription;
	
	Roles(){
		
	}
	
	public Roles(String roleId, String roleDescription) {
		super();
		this.roleId = roleId;
		this.roleDescription = roleDescription;
	}

	public String getRoleId() {
		return roleId;
	}

	public String getRoleDescription() {
		return roleDescription;
	}
	
	
	
}
