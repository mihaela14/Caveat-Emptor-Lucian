package com.biddingapp.ejbeans;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.biddingapp.login.LoginValidation;
import com.fortech.exception.AccountDetailsException;

@ManagedBean(name = "login")
@SessionScoped
public class UserLoginBean {

	@EJB
	private LoginValidation loginValidation;

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
		
		try{
		boolean validUser = loginValidation.isValidUser(accountName);
		boolean validPassword = loginValidation.isValidPassword(password, accountName);
		
		if (validUser && validPassword) {
			if(loginValidation.isAccountActivated(accountName)){
				message = "Successfully logged in";
				return "success";
			}else
				message = "Your account is not activated";
			return "notActivated";
		} else {
			message = "Invalid login";
			return "login";
		}
		}catch(AccountDetailsException ade){
			ade.setErrorMessage("Account details are not valid !");
			return "register";
		}
	}
}
