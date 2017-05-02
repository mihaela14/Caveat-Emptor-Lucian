package com.biddingapp.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@NamedQueries({
	@NamedQuery(name="findAllUsersWithName", query= "SELECT u FROM UserEntity u WHERE u.accountName = :accountName"),
	@NamedQuery(name="findAllUsersWithEmail", query= "SELECT u FROM UserEntity u WHERE u.email = :email")}) 
@Table(name="users")

public class UserEntity implements Serializable{

	private static final long serialVersionUID = -8440621083714539644L;

	@Id
	@Column(name= "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name= "first_name")
	private String firstName;

	@Column(name= "last_name")
	private String lastName;

	private String email;

	@Column(name= "account_name")
	private String accountName;

	private String password;

	@Column(name= "is_activated")
	private boolean status;

	@Column(name= "roles")
	private String roles;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}	
	public boolean getStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public String getRoles() {
		return roles;
	}
	public void setRoles(String roles) {
		this.roles = roles;
	}
	public UserEntity(int id, String firstName, String lastName, String email, String accountName, String password,boolean isActive,
			String roles) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.accountName = accountName;
		this.password = password;
		this.roles = roles;
	}
	public UserEntity() {
		super();
	}
	@Override
	public String toString() {
		return "UserEntity [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", accountName=" + accountName + ", password=" + password + ", roles=" + roles + "]";
	}
}
