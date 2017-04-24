package com.biddingapp.dao;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import com.biddingapp.entities.RegistrationEntities;
import com.biddingapp.entities.UserEntity;
import com.biddingapp.repositories.UserRepository;

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
	public RegistrationEntities getUserByActivationKey(String activationKey) {
		RegistrationEntities registrationEntities;
		try {
			registrationEntities = (RegistrationEntities) entityManager.createNamedQuery("findbyActivationKey")
					.setParameter("activationKey", activationKey).getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
		return registrationEntities;
	}

	
	@Override
	public boolean getStatusbyUsername(String accountName) {
		UserEntity userEntity = (UserEntity) entityManager.createNamedQuery("findAllUsersWithName")
				.setParameter("accountName", accountName).getSingleResult();
		return userEntity.getStatus();
	}

	
	@Override
	public UserEntity findAllUsersWithName(String accountName){
		UserEntity userEntity;
		try{
			userEntity= (UserEntity) entityManager.createNamedQuery("findAllUsersWithName").setParameter("accountName", accountName).getSingleResult();
		}catch(NoResultException nre){
			return null;
		}
		return userEntity;
	}
}
