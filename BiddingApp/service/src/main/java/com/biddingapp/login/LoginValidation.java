package com.biddingapp.login;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.openjpa.persistence.NoResultException;

import com.biddingapp.entities.UserEntity;
import com.biddingapp.repositories.UserRepository;

@Stateless
public class LoginValidation {

	@EJB
	private UserRepository userRepository;

	private static final String PERSISTENCE_UNIT_NAME = "BiddingApp";

	@PersistenceContext(unitName= PERSISTENCE_UNIT_NAME)
	private EntityManager entityManager;
	
	public boolean isValidUser(String accountName){
		
		UserEntity userEntity = userRepository.findAllUsersWithName(accountName);
		if(userEntity == null){
			return false;
		}else if(userEntity.getAccountName().equals(accountName)){
			return true;
		}
		return false;
	}


	public boolean isValidPassword(String password, String accountName){

		UserEntity userEntity= userRepository.findAllUsersWithName(accountName);
		if(userEntity == null){
			return false;
		}else if(userEntity.getPassword().equals(password)){
			return true;
		}
		return false;
	}
	


	public boolean isAccountActivated(String accountName){
		if(userRepository.getStatusbyUsername(accountName))
			return true;
		return false;
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
