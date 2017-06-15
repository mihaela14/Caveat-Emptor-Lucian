package com.biddingapp.implementations;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

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
		entityManager.remove(entityManager.merge(bid));
	}


	@Override
	public void updateBid(BiddingEntities bid) {
		entityManager.merge(bid);
	}


	@Override
	@SuppressWarnings("unchecked")
	public List<ItemsEntities> findBidsByCategory(CategoriesEntities category) throws BiddingOperationsException {
		try{
			return entityManager.createNamedQuery("findItemByCategory").setParameter("category", category).getResultList();
		}catch(PersistenceException pe){
			throw new BiddingOperationsException("An exception happended getting bids for category: "+ category.getName());
		}
	}


	@Override
	@SuppressWarnings("unchecked")
	public List<ItemsEntities> findBidsByCategoryId(int category) throws BiddingOperationsException {
		try{
			return entityManager.createNamedQuery("findItemByCategory").setParameter("category", category).getResultList();
		}catch(PersistenceException pe){			
			throw new BiddingOperationsException("An exception happended getting bids for category with id: "+ category);
		}
	}


	@Override
	@SuppressWarnings("unchecked")
	public List<BiddingEntities> findBidsByUser(UserEntity userId) throws BiddingOperationsException {
		try{
			return getEntityManager().createNamedQuery("findBidsByUser").setParameter("userId", userId).getResultList();
		}catch(PersistenceException pe){			
			throw new BiddingOperationsException("An exception happended getting bids for user with id: "+ userId.getId());
		}
	}


	@Override
	@SuppressWarnings("unchecked")
	public List<BiddingEntities> findBidsByItem(ItemsEntities itemId) throws BiddingOperationsException {
		try{
			return getEntityManager().createNamedQuery("findBidsByItem").setParameter("itemId", itemId).getResultList();
		}catch(PersistenceException pe){			
			throw new BiddingOperationsException("An exception happended getting bids for user with item id: "+ itemId.getId());
		}
	}
	
	
	@Override
	public BiddingEntities findBidByItemUser(int userId, int itemId) throws BiddingOperationsException {
		try{
			return (BiddingEntities) getEntityManager().createNamedQuery("findBidByItemUser").setParameter("userId", userId).setParameter("itemId", itemId).getSingleResult();
		}catch(PersistenceException pe){
			throw new BiddingOperationsException("An excepiton happened getting the bid for the item id:"+ itemId + "and user id" + userId);
		}
	}


	@Override
	public BiddingEntities findbidById(int id) {
		return entityManager.find(BiddingEntities.class, id);
	}


	@Override
	public Long getBidsNumber(int itemId) throws BiddingOperationsException {
		try{
			return (Long) getEntityManager().createNamedQuery("countBidsByItem").setParameter("itemId", itemId).getSingleResult();
		}catch(PersistenceException pe){
			throw new BiddingOperationsException("An exception happened trying to count the bids for the item id:" + itemId);
		}
	}


	@Override
	public Float findMaxBid(int itemId) throws BiddingOperationsException {
		try{
			Query query= entityManager.createNamedQuery("maxBidByItem").setParameter("itemId", itemId);
			return (Float)query.getSingleResult();
		}catch(PersistenceException pe){
			throw new BiddingOperationsException("An exception happened trying to get the max result for the item id"+ itemId);
		}
	}


	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
}
