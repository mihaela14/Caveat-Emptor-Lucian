package com.biddingapp.ejbeans;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import com.biddingapp.login.LoginValidation;
import com.fortech.exception.AccountDetailsException;

@Named(value="login")
@SessionScoped
public class UserLoginBean implements Serializable{

	private static final long serialVersionUID = -1341006596838933518L;

	@EJB
	private LoginValidation loginValidation;

	private String message = "Enter username and password";
	private String accountName;
	private String password;
	private boolean isAdmin;



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
		isAdmin= loginValidation.getUserAuthorization(accountName);
		
		if (validUser && validPassword) {
			if(loginValidation.isAccountActivated(accountName)){
				message = "Successfully logged in";
				return "items-page";
			}else
				message = "Your account is not activated";
			return "notActivated";
		} else {
			message = "Invalid login";
			return "login";
		}
		}catch(AccountDetailsException ade){
			ade.setErrorMessage("Account details are not valid !");
			message = "Invalid login";
			return "login";
		}
	}
	
	
	public String getAccountName() {
		return accountName;
	}
	
	public boolean isLoggedIn() {
		return getAccountName() != null ? true : false;
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

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
}
