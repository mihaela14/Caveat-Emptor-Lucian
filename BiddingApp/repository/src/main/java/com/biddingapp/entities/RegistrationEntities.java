package com.biddingapp.entities;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@NamedQuery(name="findbyActivationKey", query= "SELECT u FROM RegistrationEntities u WHERE u.activationKey = :activationKey")
@Table(name= "registrationdetails")
public class RegistrationEntities implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name= "ID")	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@OneToOne(cascade= CascadeType.PERSIST)
	@JoinColumn(name="user_id")
	private UserEntity user;
	
	@Column(name= "activation_expiry")
	private Timestamp  activationExpiry;
	
	@Column(name= "activation_key")
	private String activationKey;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Timestamp getActivationExpiry() {
		return activationExpiry;
	}
	public void setActivationExpiry(Timestamp activationExpiry) {
		this.activationExpiry = activationExpiry;
	}
	
	public UserEntity getUserid() {
		return user;
	}
	public void setUserid(UserEntity userid) {
		this.user = userid;
	}
	public String getActivationKey() {
		return activationKey;
	}
	public void setActivationKey(String activationKey) {
		this.activationKey = activationKey;
	}
	
	

}
