package com.biddingapp.implementations;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import com.biddingapp.entities.ItemsEntities;
import com.biddingapp.entities.UserEntity;
import com.biddingapp.repositories.ItemsRepository;
import com.fortech.exception.ItemsDetailsException;

@Stateless
@Remote(ItemsRepository.class)
public class ItemsRepositoryImplementation implements ItemsRepository{

	private static final String PERSISTENCE_UNIT_NAME = "BiddingApp";

	@PersistenceContext(unitName = PERSISTENCE_UNIT_NAME)
	private EntityManager entityManager;


	@Override
	public void add(ItemsEntities itemsEntities) {
		entityManager.persist(itemsEntities);
	}

	@Override
	public void remove(int id) {
		ItemsEntities itemsEntities = entityManager.find(ItemsEntities.class, id);
		if (itemsEntities != null) {
			entityManager.remove(itemsEntities);
		}
	}

	@Override
	public void updateItem(ItemsEntities itemsEntities) {
		entityManager.merge(itemsEntities);	
	}


	@Override
	public List<ItemsEntities> findEntitiesbyUser(UserEntity user) throws ItemsDetailsException{
		try{
			List<ItemsEntities>itemsEntities= new ArrayList<>();
			itemsEntities = entityManager.createNamedQuery("findItemByUser")
					.setParameter("sellerId", user.getId()).getResultList();
			return itemsEntities;
		}catch(NoResultException nre){
			throw new ItemsDetailsException();
		}
	}


	public EntityManager getEntityManager() {
		return entityManager;
	}
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}


	public ItemsRepositoryImplementation(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	public ItemsRepositoryImplementation() {
	}
}
