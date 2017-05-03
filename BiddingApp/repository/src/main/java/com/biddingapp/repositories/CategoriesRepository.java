package com.biddingapp.repositories;

import com.biddingapp.entities.CategoriesEntities;
import com.fortech.exception.CategoriesDetailsException;

public interface CategoriesRepository {
	
	public void addCategory(CategoriesEntities categoriesEntities);
	public void removeCategory(int id) throws CategoriesDetailsException;
	public void updateCategoryParent(CategoriesEntities categoriesEntities);
	public CategoriesEntities findCategorybyId(int id);
	public CategoriesEntities findCategorybyName(String name) throws CategoriesDetailsException;
	public void updateCategory(CategoriesEntities categoriesEntities);

}
