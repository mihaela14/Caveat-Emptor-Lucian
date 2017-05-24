package com.biddingapp.login;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.biddingapp.entities.UserEntity;
import com.biddingapp.repositories.UserRepository;
import com.fortech.exception.AccountDetailsException;

@Stateless
public class LoginValidation {

	@EJB
	private UserRepository userRepository;

	private static final String PERSISTENCE_UNIT_NAME = "BiddingApp";

	@PersistenceContext(unitName= PERSISTENCE_UNIT_NAME)
	private EntityManager entityManager;

	public boolean isValidUser(String accountName) throws AccountDetailsException{
		UserEntity userEntity = userRepository.findAllUsersWithName(accountName);
		return userEntity.getAccountName().equals(accountName);
	}


	public boolean isValidPassword(String password, String accountName) throws AccountDetailsException{
		UserEntity userEntity= userRepository.findAllUsersWithName(accountName);
		if(userEntity == null){
			return false;
		}else if(userEntity.getPassword().equals(password)){
			return true;
		}
		return false;
	}


	public boolean isAccountActivated(String accountName) throws AccountDetailsException{
		return userRepository.getStatusbyUsername(accountName);
	}


	public UserRepository getUserRepository() {
		return userRepository;
	}
	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
}
