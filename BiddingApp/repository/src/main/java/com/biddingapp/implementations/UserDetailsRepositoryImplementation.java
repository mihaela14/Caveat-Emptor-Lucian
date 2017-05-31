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
import com.fortech.exception.AccountDetailsException;
import com.fortech.exception.ItemsDetailsException;
import com.fortech.exception.UserDetailsException;

@Stateless
@Remote(UserDetailsRepository.class)
public class UserDetailsRepositoryImplementation implements UserDetailsRepository{

	private static final String PERSISTENCE_UNIT_NAME = "BiddingApp";

	@PersistenceContext(unitName = PERSISTENCE_UNIT_NAME)
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public List<UserEntity> findAllUsers() throws UserDetailsException{
		try{
			return getEntityManager().createNamedQuery("findAllUsers").getResultList();
		}catch(PersistenceException pe){
			throw new UserDetailsException("An exception happened while trying to get users list");
		}
	}

	
	@Override
	public Long findNumberOfItemsPlaced(int sellerId) {
		return (Long)getEntityManager().createNamedQuery("findItemsCountByUser").setParameter("sellerId", sellerId).getSingleResult();
	}
	
	
	@Override
	public Long findNumberOfItemsSold(int sellerId) {
		return (Long) getEntityManager().createNamedQuery("findItemsCountSoldByUser").setParameter("sellerId", sellerId).getSingleResult();
	}

	
	@Override
	public Long findNumberOfItemsBought(int winnerId) {
		return (Long) getEntityManager().createNamedQuery("findItemsCountByWinner").setParameter("winnerId", winnerId).getSingleResult();
	}


	public EntityManager getEntityManager() {
		return entityManager;
	}
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

}
