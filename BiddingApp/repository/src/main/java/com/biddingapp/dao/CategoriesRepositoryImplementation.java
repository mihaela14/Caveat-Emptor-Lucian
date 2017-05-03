package com.biddingapp.dao;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.biddingapp.entities.CategoriesEntities;
import com.biddingapp.repositories.CategoriesRepository;
import com.fortech.exception.CategoriesDetailsException;

@Stateless
@Remote(CategoriesRepository.class)
public class CategoriesRepositoryImplementation implements CategoriesRepository {

	private static final String PERSISTENCE_UNIT_NAME = "BiddingApp";

	@PersistenceContext(unitName = PERSISTENCE_UNIT_NAME)
	private EntityManager entityManager;

	@Override
	public void addCategory(CategoriesEntities categoriesEntities) {
		entityManager.persist(categoriesEntities);	
	}


	@Override
	public void removeCategory(int id) throws CategoriesDetailsException{
		CategoriesEntities categoriesEntities = entityManager.find(CategoriesEntities.class, id);
		if (categoriesEntities != null) {
			updateCategoryParent(categoriesEntities);
			entityManager.remove(categoriesEntities);
		}
	}


	@Override
	public void updateCategoryParent(CategoriesEntities categoriesEntities) {
		for(CategoriesEntities child: categoriesEntities.getChildren()){
			child.setParent(categoriesEntities.getParent());
			entityManager.merge(child);
		}
	}

	public void updateCategory(CategoriesEntities categoriesEntities) {		
		entityManager.merge(categoriesEntities);
	}


	@Override
	public CategoriesEntities findCategorybyId(int id) {
		return entityManager.find(CategoriesEntities.class, id);
	}

	@Override
	public CategoriesEntities findCategorybyName(String name) throws CategoriesDetailsException{
		CategoriesEntities categoriesEntities;
		categoriesEntities = (CategoriesEntities) entityManager.createNamedQuery("findCategorybyName")
				.setParameter("name", name).getSingleResult();
		return categoriesEntities;
	}
}

