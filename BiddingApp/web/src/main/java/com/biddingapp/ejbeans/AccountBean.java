package com.biddingapp.ejbeans;

import java.util.regex.Matcher;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import com.biddingapp.account.AccountService;
import com.biddingapp.entities.AccountEntities;
import com.biddingapp.entities.UserEntity;
import com.biddingapp.register.RegisterValidation;
import com.fortech.dto.AccountDTO;
import com.fortech.dto.UserDTO;
import com.fortech.exception.AccountDetailsException;
import com.fortech.utils.Constants;

@ManagedBean(name = "account")
@SessionScoped
public class AccountBean {

	@ManagedProperty(value = "#{login}")
	private UserLoginBean loggedUserDetails;

	@EJB
	private AccountService accountService;

	@EJB
	private RegisterValidation registerValidation;

	private AccountDTO accountDto;

	private UserDTO userDto;

	private boolean isAccountDetailsEditable;

	private boolean isShippingEditable;

	private UserEntity userEntity;

	private String currentPassword;


	@PostConstruct
	public void init() {
		userEntity= accountService.getDetailsForUser(loggedUserDetails.getAccountName());
		userDto= getUserDto(userEntity);
		accountDto= getAccountDto(accountService.getAccountForUser(userDto.getId()));
	}


	public void submitAccountChanges(){
		accountService.updateUser(getUserEntity(userDto));
		getAccountEditStatus();

	}


	public void submitShippingChanges(){
		accountService.updateAccount(getAccountEntity(accountDto));
		getShippingEditStatus();
	}


	private UserEntity getUserEntity(UserDTO userDto) {

		userEntity.setFirstName(userDto.getFirstName());
		userEntity.setLastName(userDto.getLastName());
		userEntity.setEmail(userDto.getEmail());

		if(userDto.getPassword() != null){
			userEntity.setPassword(userDto.getPassword());
		}

		return userEntity;
	}


	private AccountEntities getAccountEntity(AccountDTO accountDto) {
		AccountEntities account= new AccountEntities();

		account.setAdress(accountDto.getAdress());
		account.setCity(accountDto.getCity());
		account.setPhone(accountDto.getPhone());
		account.setZipcode(accountDto.getZipcode());
		account.setUserId(accountService.getUserForAccount(accountDto.getUserId()));
		account.setId(accountDto.getId());

		return account;
	}


	private UserDTO getUserDto(UserEntity detailsForUser) {
		UserDTO userDto= new UserDTO();

		userDto.setAccountName(detailsForUser.getAccountName());
		userDto.setFirstName(detailsForUser.getFirstName());
		userDto.setLastName(detailsForUser.getLastName());
		userDto.setPassword(detailsForUser.getPassword());
		userDto.setEmail(detailsForUser.getEmail());
		userDto.setRoles(detailsForUser.getRoles());
		userDto.setId(detailsForUser.getId());

		return userDto;
	}

	private AccountDTO getAccountDto(AccountEntities account){
		AccountDTO accountDto= new AccountDTO();

		accountDto.setAdress(account.getAdress());
		accountDto.setCity(account.getCity());
		accountDto.setPhone(account.getPhone());
		accountDto.setUserId(account.getUserId().getId());
		accountDto.setZipcode(account.getZipcode());
		accountDto.setId(account.getId());

		return accountDto;
	}


	public void getAccountEditStatus(){
		isAccountDetailsEditable= !isAccountDetailsEditable;
	}

	public void getShippingEditStatus(){
		isShippingEditable= !isShippingEditable;
	}


	public void isOldPasswordValid(FacesContext context, UIComponent componentToValidate, Object value){
		if(value!= null){
			String currentPassword= value.toString();

			if(!currentPassword.equals(userDto.getPassword())){
				FacesMessage message= new FacesMessage("Password does not match");
				throw new ValidatorException(message);
			}
		}
	}


	public void isNewEmailValid(FacesContext context, UIComponent componentToValidate, Object value){
		try{
			String email= value.toString();
			Matcher matcher = Constants.VALID_EMAIL_ADDRESS_REGEX.matcher(email);

			if(!matcher.find()){
				FacesMessage message= new FacesMessage("*Email is not valid");
				throw new ValidatorException(message);
			}else if(getRegisterValidation().isDuplicateEmail(email) && !email.equals(userDto.getEmail())){
				FacesMessage message= new FacesMessage("Email already exists");
				throw new ValidatorException(message);
			}
		}catch(AccountDetailsException ade){
			FacesMessage message= new FacesMessage("Email is not valid");
		}
	}
	
	
	public void isPhoneNumberValid(FacesContext context, UIComponent componentToValidate, Object value){
			String phone= value.toString();
			
			if(phone.length()!=9){
				FacesMessage message= new FacesMessage("Phone Number must be 9");
				throw new ValidatorException(message);
			}
	}


	public UserLoginBean getLoggedUserDetails() {
		return loggedUserDetails;
	}
	public void setLoggedUserDetails(UserLoginBean loggedUserDetails) {
		this.loggedUserDetails = loggedUserDetails;
	}
	public AccountDTO getAccountDto() {
		return accountDto;
	}
	public void setAccountDto(AccountDTO accountDto) {
		this.accountDto = accountDto;
	}
	public AccountService getAccountService() {
		return accountService;
	}
	public void setAccountService(AccountService accountService) {
		this.accountService = accountService;
	}
	public UserDTO getUser() {
		return userDto;
	}
	public void setUser(UserDTO userDto) {
		this.userDto = userDto;
	}
	public UserDTO getUserDto() {
		return userDto;
	}
	public void setUserDto(UserDTO userDto) {
		this.userDto = userDto;
	}
	public boolean isAccountDetailsEditable() {
		return isAccountDetailsEditable;
	}
	public void setAccountDetailsEditable(boolean isAccountDetailsEditable) {
		this.isAccountDetailsEditable = isAccountDetailsEditable;
	}
	public boolean isShippingEditable() {
		return isShippingEditable;
	}
	public void setShippingEditable(boolean isShippingEditable) {
		this.isShippingEditable = isShippingEditable;
	}


	public UserEntity getUserEntity() {
		return userEntity;
	}


	public void setUserEntity(UserEntity userEntity) {
		this.userEntity = userEntity;
	}


	public RegisterValidation getRegisterValidation() {
		return registerValidation;
	}


	public void setRegisterValidation(RegisterValidation registerValidation) {
		this.registerValidation = registerValidation;
	}


	public String getCurrentPassword() {
		return currentPassword;
	}


	public void setCurrentPassword(String currentPassword) {
		this.currentPassword = currentPassword;
	}	
}
