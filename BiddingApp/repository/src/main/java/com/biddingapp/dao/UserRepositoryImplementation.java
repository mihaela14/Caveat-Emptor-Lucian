package com.biddingapp.dao;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import com.biddingapp.entities.RegistrationEntities;
import com.biddingapp.entities.UserEntity;
import com.biddingapp.repositories.UserRepository;
import com.fortech.exception.AccountDetailsException;

@Stateless
@Remote(UserRepository.class)
public class UserRepositoryImplementation implements UserRepository {

	private static final String PERSISTENCE_UNIT_NAME = "BiddingApp";

	@PersistenceContext(unitName = PERSISTENCE_UNIT_NAME)
	private EntityManager entityManager;

	public UserRepositoryImplementation() {
		super();
	}

	public UserRepositoryImplementation(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}


	@Override
	public void addUser(UserEntity userEntity) {
		entityManager.persist(userEntity);
	}


	@Override
	public void addRegistration(RegistrationEntities registrationEntity) {
		entityManager.persist(registrationEntity);
	}


	@Override
	public void removeUser(int id) {
		UserEntity userEntity = entityManager.find(UserEntity.class, id);
		if (userEntity != null) {
			entityManager.remove(userEntity);
		}
	}


	@Override
	public void removeRegistration(int id) {
		RegistrationEntities registerEntity = entityManager.find(RegistrationEntities.class, id);
		if (registerEntity != null) {
			entityManager.remove(registerEntity);
		}
	}


	@Override
	public void updateUser(UserEntity userEntity) {
		entityManager.merge(userEntity);
	}


	@Override
	public UserEntity findUserbyID(int id) {
		return entityManager.find(UserEntity.class, id);
	}


	@Override
	public RegistrationEntities getUserByActivationKey(String activationKey) throws AccountDetailsException{
		try{	
			RegistrationEntities registrationEntities;
			registrationEntities = (RegistrationEntities) entityManager.createNamedQuery("findbyActivationKey")
					.setParameter("activationKey", activationKey).getSingleResult();
			return registrationEntities;
		}catch(NoResultException ne){
			throw new AccountDetailsException();
		}
	}


	@Override
	public boolean getStatusbyUsername(String accountName) throws AccountDetailsException{
		try{
			UserEntity userEntity = (UserEntity) entityManager.createNamedQuery("findAllUsersWithName")
					.setParameter("accountName", accountName).getSingleResult();
			return userEntity.getStatus();
		}catch(NoResultException ne){
			throw new AccountDetailsException();
		}
	}


	@Override
	public UserEntity findAllUsersWithName(String accountName) throws AccountDetailsException{
		try{
			UserEntity userEntity;
			userEntity= (UserEntity) entityManager.createNamedQuery("findAllUsersWithName").setParameter("accountName", accountName).getSingleResult();
			return userEntity;
		}catch(NoResultException ne){
			throw new AccountDetailsException();
		}
	}

	@Override
	public UserEntity findAllUsersByEmail(String email) throws AccountDetailsException {
		try{
			UserEntity userEntity;
			userEntity= (UserEntity) entityManager.createNamedQuery("findAllUsersWithEmail").setParameter("email", email).getSingleResult();
			return userEntity;
		}catch(NoResultException ne){
			throw new AccountDetailsException();
		}
	}
}
