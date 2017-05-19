package com.biddingapp.queries;

public class ItemsQueries {

	public static final String FIND_ITEM_BY_USER= "SELECT i FROM ItemsEntities i WHERE i.sellerId = :sellerId";
	
	public static final String FIND_ITEM_BY_CATEGORY= "SELECT i FROM ItemsEntities i WHERE i.category.id = :category";
	
	public static final String FIND_ITEM_BY_MULETIPLE_CATEGORIES= "SELECT i FROM ItemsEntities i WHERE i.category.id IN (:categoriesList)";
	
}
