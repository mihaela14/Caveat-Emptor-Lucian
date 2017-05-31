package com.biddingapp.implementations;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import com.biddingapp.entities.AccountEntities;
import com.biddingapp.repositories.AccountRepository;
import com.fortech.exception.AccountDetailsException;

@Stateless
@Remote(AccountRepository.class)
public class AccountRepositoryImplementation implements AccountRepository{
	
	private static final String PERSISTENCE_UNIT_NAME = "BiddingApp";

	@PersistenceContext(unitName = PERSISTENCE_UNIT_NAME)
	private EntityManager entityManager;

	
	@Override
	public AccountEntities findAccountForUser(int userId) throws AccountDetailsException {
		try{
			return (AccountEntities) getEntityManager().createNamedQuery("findAccountByUser").setParameter("userId", userId).getSingleResult();
		}catch(PersistenceException pe){
			throw new AccountDetailsException("An error happenet getting account details for user with the id: "+ userId);
		}
	}
	
	
	public EntityManager getEntityManager() {
		return entityManager;
	}
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
}
