package com.biddingapp.implementations;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import com.biddingapp.entities.BiddingEntities;
import com.biddingapp.entities.CategoriesEntities;
import com.biddingapp.entities.ItemsEntities;
import com.biddingapp.entities.UserEntity;
import com.biddingapp.repositories.BiddingRepository;
import com.fortech.exception.BiddingOperationsException;

@Stateless
@Remote(BiddingRepository.class)
public class BiddingRepositoryImplementation implements BiddingRepository{

	private static final String PERSISTENCE_UNIT_NAME = "BiddingApp";

	@PersistenceContext(unitName = PERSISTENCE_UNIT_NAME)
	private EntityManager entityManager;

	
	@Override
	public void addBid(BiddingEntities bid) {
		entityManager.persist(bid);
	}

	
	@Override
	public void removeBidById(int id) {
		BiddingEntities bid = entityManager.find(BiddingEntities.class, id);
		entityManager.remove(bid);
	}

	
	@Override
	public void removeBidbyEntity(BiddingEntities bid) {
		entityManager.remove(bid);
	}

	
	@Override
	public void updateBid(BiddingEntities bid) {
		entityManager.merge(bid);
	}
	

	@Override
	public BiddingEntities findBidsByUser() {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	@SuppressWarnings("unchecked")
	public List<ItemsEntities> findBidsByCategory(CategoriesEntities category) throws BiddingOperationsException {
		try{
		return entityManager.createNamedQuery("findItemByCategory").setParameter("category", category).getResultList();
		}catch(PersistenceException pe){
			throw new BiddingOperationsException();
		}
	}
	

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
}
