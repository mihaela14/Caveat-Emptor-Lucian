package com.biddingapp.ejbeans;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import com.biddingapp.account.AccountService;
import com.biddingapp.entities.AccountEntities;
import com.biddingapp.entities.UserEntity;
import com.fortech.dto.AccountDTO;
import com.fortech.dto.UserDTO;

@ManagedBean(name = "account")
@SessionScoped
public class AccountBean {

	@ManagedProperty(value = "#{login}")
	private UserLoginBean loggedUserDetails;
	
	@EJB
	private AccountService accountService;
	
	private AccountDTO accountDto;
	
	private UserDTO userDto;
	
	
	@PostConstruct
	public void init() {
		userDto= getUserDto(accountService.getDetailsForUser(loggedUserDetails.getAccountName()));
		accountDto= getAccountDto(accountService.getAccountForUser(userDto.getId()));
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
	
}
