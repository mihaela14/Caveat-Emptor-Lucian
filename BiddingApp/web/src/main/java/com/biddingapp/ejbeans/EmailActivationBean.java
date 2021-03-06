package com.biddingapp.ejbeans;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import com.biddingapp.register.RegisterValidation;
import com.fortech.exception.AccountDetailsException;

@ManagedBean(name = "activation")
@RequestScoped
public class EmailActivationBean {

	@ManagedProperty(value="#{param.key}")
	private String key;
	
	@EJB
	RegisterValidation registerValidation;

	private boolean valid;

	@PostConstruct
	public void init() {
		
		try {
			if(registerValidation.activateUserByKey(getKey())){
				valid= true;
			}else{
				valid= false;
			}
		} catch (AccountDetailsException e) {
			valid=false;
			e.printStackTrace();
		}
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
}