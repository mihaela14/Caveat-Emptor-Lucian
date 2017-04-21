package com.biddingapp.dao;

import java.util.Collection;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transaction;

import com.biddingapp.entities.RegistrationEntities;
import com.biddingapp.entities.UserEntity;
import com.biddingapp.repositories.UserRepository;
import com.fortech.dto.RegistrationDTO;
import com.fortech.dto.UserDTO;

@Stateless
@Remote(UserRepository.class)
public class UserRepositoryDAO implements UserRepository {

	private static final String PERSISTENCE_UNIT_NAME = "BiddingApp";

	@PersistenceContext(unitName= PERSISTENCE_UNIT_NAME)
	private EntityManager entityManager;

	public UserRepositoryDAO() {
		super();
	}

	public UserRepositoryDAO(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}

	public void addUser(RegistrationEntities registerEntity) {
		entityManager.persist(registerEntity);
	}

	public void removeUser(int id) {
		UserEntity userEntity = entityManager.find(UserEntity.class, id);
		if (userEntity != null) {
			entityManager.remove(userEntity);
		}
	}

	public void updateUser(RegistrationDTO userEntity) {
		entityManager.persist(userEntity);
	}

	public UserEntity findUserbyID(int id) {
		return entityManager.find(UserEntity.class, id);
	}

	public UserEntity findRegistrationbyID(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean isValidUser(String accountName){
		try{
			UserEntity userEntity= (UserEntity) entityManager.createNamedQuery("findAllUsersWithName").setParameter("accountName", accountName).getSingleResult();
			if(userEntity.getAccountName().equals(accountName)){
				return true;
			}
		}catch(NoResultException nre){
			return false;
		}
		return false;
	}

	public boolean isValidPassword(String password, String accountName){
		try{
			UserEntity userEntity= (UserEntity) entityManager.createNamedQuery("findAllUsersWithName").setParameter("accountName", accountName).getSingleResult();
			if(userEntity.getPassword().equals(password)){
				return true;
			}
		}catch(NoResultException nre){
			return false;
		}
		return false;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public int getIdByActivationKey(String activationKey) {
		RegistrationEntities registrationEntities= (RegistrationEntities) entityManager.createNamedQuery("registrationdetails").setParameter("activationKey", activationKey).getSingleResult();
		return registrationEntities.getId();
	}
}

