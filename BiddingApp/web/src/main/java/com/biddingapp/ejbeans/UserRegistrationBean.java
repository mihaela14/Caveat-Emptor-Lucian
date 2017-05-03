package com.biddingapp.ejbeans;

import java.util.regex.Matcher;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import com.biddingapp.email.EmailService;
import com.biddingapp.register.RegisterValidation;
import com.fortech.exception.AccountDetailsException;
import com.fortech.implementations.UserDTO;
import com.fortech.utils.Constants;

@ManagedBean(name = "registration")
@SessionScoped
public class UserRegistrationBean {

	private String firstName;
	private String lastName;
	private String email;
	private String accountName;
	private String password;

	@EJB
	private RegisterValidation registerValidation;

	@PostConstruct
	public void init() {
		System.err.println("Init!!!!!");
	}

	public RegisterValidation getRegisterValidation() {
		return registerValidation;
	}
	public void setRegisterValidation(RegisterValidation registerValidation) {
		this.registerValidation = registerValidation;
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


	public UserDTO getUserDTO(){
		UserDTO createDto= new UserDTO();

		createDto.setFirstName(firstName);
		createDto.setLastName(lastName);
		createDto.setEmail(email);
		createDto.setAccountName(accountName);
		createDto.setPassword(password);
		createDto.setStatus(false);
		createDto.setRoles("USER");

		return createDto;
	}


	public String register(){	
		String key = EmailService.sendEmail(email);
		registerValidation.registerUser(getUserDTO(), key);
		return "post-registration";
	}


	public void isValidUsername(FacesContext context, UIComponent componentToValidate, Object value){
		try{
			String username= value.toString();
			if(username.length()<4){
				FacesMessage message= new FacesMessage("*Username is not valid (Username must be at least 4 characters long)");
				throw new ValidatorException(message);
			}else if(registerValidation.isDuplicateUsername(username)){
				FacesMessage message= new FacesMessage("*Username already exists");
				throw new ValidatorException(message);
			}
		}catch(AccountDetailsException ade){
			FacesMessage message= new FacesMessage("*Username is not valid");
		}
	}


	public void IsValidEmail(FacesContext context, UIComponent componentToValidate, Object value){
		try{
			String email= value.toString();
			Matcher matcher = Constants.VALID_EMAIL_ADDRESS_REGEX.matcher(email);
			
			if(!matcher.find()){
				FacesMessage message= new FacesMessage("*Email is not valid");
				throw new ValidatorException(message);
			}else if(registerValidation.isDuplicateEmail(email)){
				FacesMessage message= new FacesMessage("*Email already exists");
				throw new ValidatorException(message);
			}
		}catch(AccountDetailsException ade){
			FacesMessage message= new FacesMessage("*Email is not valid");
		}
	}
}
