package com.fortech.dto;

import java.sql.Timestamp;

public class RegistrationDTO {
	
	private int id;
	private Timestamp  activationExpiry;
	private boolean isActivate;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Timestamp getActivationExpiry() {
		return (Timestamp) activationExpiry.clone();
	}
	public void setActivationExpiry(Timestamp activationExpiry) {
		this.activationExpiry = activationExpiry;
	}
	public boolean isActivate() {
		return isActivate;
	}
	public void setActivate(boolean isActivate) {
		this.isActivate = isActivate;
	}
	
	public RegistrationDTO(int id, Timestamp activationExpiry, boolean isActivate) {
		super();
		this.id = id;
		this.activationExpiry = activationExpiry;
		this.isActivate = isActivate;
	}
	
	public RegistrationDTO() {
		super();
	}

}
