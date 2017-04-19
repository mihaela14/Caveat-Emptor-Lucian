package com.biddingapp.ejbeans;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.biddingapp.login.LoginValidation;
import com.biddingapp.repositories.UserRepository;

@ManagedBean(name = "login")
@SessionScoped
public class UserSessionBean {

	@EJB
	private LoginValidation loginValidation;

	@PostConstruct
	public void init() {
		System.err.println("Init!!!!!");
	}

	private String message = "Enter username and password";
	private String accountName;
	private String password;

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

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public LoginValidation getLoginValidation() {
		return loginValidation;
	}

	public void setLoginValidation(LoginValidation loginValidation) {
		this.loginValidation = loginValidation;
	}

	public String loginUser(){
		boolean loginState= getLoginValidation().validateUsernamePassword(accountName, password);
		if (!loginState) {
			message = "Invalid login";
			return "login";
		} else {
			message = "Successfully logged in";
			return "success";
		}
	}
}
