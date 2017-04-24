package com.biddingapp.repositories;

import com.biddingapp.entities.CategoriesEntities;

public interface CategoriesRepository {
	
	public void addCategory(CategoriesEntities categoriesEntities);
	public void removeCategory(int id);
	public void updateCategory(CategoriesEntities categoriesEntities);
	public CategoriesEntities findCategorybyId(int id);
	public CategoriesEntities findCategorybyName(String name);

}
