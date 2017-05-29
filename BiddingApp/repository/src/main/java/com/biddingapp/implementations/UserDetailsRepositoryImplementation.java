package com.biddingapp.implementations;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import org.apache.openjpa.util.UserException;

import com.biddingapp.entities.UserEntity;
import com.biddingapp.repositories.ItemsRepository;
import com.biddingapp.repositories.UserDetailsRepository;

@Stateless
@Remote(UserDetailsRepository.class)
public class UserDetailsRepositoryImplementation implements UserDetailsRepository{

	private static final String PERSISTENCE_UNIT_NAME = "BiddingApp";

	@PersistenceContext(unitName = PERSISTENCE_UNIT_NAME)
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public List<UserEntity> findAllUsers() throws UserException{
		try{
			return entityManager.createNamedQuery("findAllUsers").getResultList();
		}catch(PersistenceException pe){
			throw new UserException("An exception happened while trying to get users list");
		}
	}

}
