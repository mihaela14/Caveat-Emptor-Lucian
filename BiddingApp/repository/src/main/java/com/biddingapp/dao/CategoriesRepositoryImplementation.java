package com.biddingapp.dao;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.biddingapp.entities.CategoriesEntities;
import com.biddingapp.entities.RegistrationEntities;
import com.biddingapp.entities.UserEntity;
import com.biddingapp.repositories.CategoriesRepository;
import com.biddingapp.repositories.UserRepository;

@Stateless
@Remote(CategoriesRepository.class)
public class CategoriesRepositoryImplementation implements CategoriesRepository {

	private static final String PERSISTENCE_UNIT_NAME = "BiddingApp";

	@PersistenceContext(unitName = PERSISTENCE_UNIT_NAME)
	private EntityManager entityManager;

	@Override
	public void addCategory(CategoriesEntities categoriesEntities) {
		entityManager.remove(categoriesEntities);	
	}


	@Override
	public void removeCategory(int id) {
		CategoriesEntities categoriesEntities = entityManager.find(CategoriesEntities.class, id);
		if (categoriesEntities != null) {
			entityManager.remove(categoriesEntities);
		}

	}


	@Override
	public void updateCategory(CategoriesEntities categoriesEntities) {
		entityManager.merge(categoriesEntities);
	}


	@Override
	public CategoriesEntities findCategorybyId(int id) {
		return entityManager.find(CategoriesEntities.class, id);
	}

	@Override
	public CategoriesEntities findCategorybyName(String name) {
		CategoriesEntities categoriesEntities;
		categoriesEntities = (CategoriesEntities) entityManager.createNamedQuery("findCategorybyName")
				.setParameter("name", name).getSingleResult();
	return categoriesEntities;
	}
}

