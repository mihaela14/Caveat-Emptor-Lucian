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
import com.fortech.utils.Constants;

import dto.UserDTO;

@ManagedBean(name = "registration")
@SessionScoped
public class UserRegistrationBean {

	@EJB
	private RegisterValidation registerValidation;

	private UserDTO userDTO;

	@PostConstruct
	public void init() {
		userDTO = new UserDTO();
	}

	public RegisterValidation getRegisterValidation() {
		return registerValidation;
	}
	public void setRegisterValidation(RegisterValidation registerValidation) {
		this.registerValidation = registerValidation;
	}
	public UserDTO getUserDTO() {
		return userDTO;
	}

	public void setUserDTO(UserDTO userDTO) {
		this.userDTO = userDTO;
	}


	public String register(){	
		String key = EmailService.sendEmail(userDTO.getEmail());
		registerValidation.registerUser(userDTO, key);
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
