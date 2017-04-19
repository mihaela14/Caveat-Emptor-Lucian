package com.biddingapp.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name= "registrationdetails")
public class RegistrationEntities {
	
	private int id;
	private long  activationExpiry;
	private boolean isActivate;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public long getActivationExpiry() {
		return activationExpiry;
	}
	public void setActivationExpiry(long activationExpiry) {
		this.activationExpiry = activationExpiry;
	}
	public boolean isActivate() {
		return isActivate;
	}
	public void setActivate(boolean isActivate) {
		this.isActivate = isActivate;
	}
	
	

}
