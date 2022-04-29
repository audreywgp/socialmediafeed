package com.dxc.socialmedia.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="user_details")
public class TableUserDetails extends Auditable<String> {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="user_id")
	private int userId;
	
	@Column(name="u_name")
	private String name;
	
	@Column(name="username",unique=true)
	private String username;
	
	@Column(name="u_password")
	private String password;
	
	@Column(name="u_role")
	private String role;
	
	@Column(name="is_available")
	private int isAvailable ;
	
//	@OneToMany(mappedBy="userId", cascade=CascadeType.ALL)
//	private UserData userData;
	
	
	public int getUserId() {
		return userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public int getIsAvailable() {
		return isAvailable;
	}

	public void setIsAvailable(int isAvailable) {
		this.isAvailable = isAvailable;
	}
	
	public TableUserDetails() {
		
	}
	
	public TableUserDetails(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	@Override
	public String toString() {
		return "UserDetails [username=" + username + ", password=" + password + ", role=" + role + "]";
	}
	
	

}
