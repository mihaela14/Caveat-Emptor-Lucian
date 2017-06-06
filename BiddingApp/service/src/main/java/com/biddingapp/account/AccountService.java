package com.biddingapp.account;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.biddingapp.entities.AccountEntities;
import com.biddingapp.entities.UserEntity;
import com.biddingapp.repositories.AccountRepository;
import com.biddingapp.repositories.UserRepository;
import com.fortech.exception.AccountDetailsException;

@Stateless
public class AccountService {

	@EJB
	private AccountRepository accountRepository;

	@EJB
	private UserRepository userRepository;


	public UserEntity getDetailsForUser(String accountName){
		try {
			return userRepository.findAllUsersWithName(accountName);
		} catch (AccountDetailsException e) {
			e.printStackTrace();
			return null;
		}
	}


	public AccountEntities getAccountForUser(int userId){
		try {
			return accountRepository.findAccountForUser(userId);
		} catch (AccountDetailsException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	public UserEntity getUserForAccount(int userId){
		return userRepository.findUserbyID(userId);
	}


	public AccountRepository getAccountRepository() {
		return accountRepository;
	}
	public void setAccountRepository(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}
	public UserRepository getUserRepository() {
		return userRepository;
	}
	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}


	public void updateUser(UserEntity user) {
		userRepository.updateUser(user);
	}


	public void updateAccount(AccountEntities account) {
		accountRepository.updateAccount(account);
	}
}
