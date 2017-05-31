package com.biddingapp.queries;

public class ItemsQueries {

	public static final String FIND_ITEM_BY_USER= "SELECT i FROM ItemsEntities i WHERE i.sellerId = :sellerId";
	
	public static final String FIND_ITEM_BY_CATEGORY= "SELECT i FROM ItemsEntities i WHERE i.category.id = :category";
	
	public static final String FIND_ITEMS_COUNT_BY_USER= "SELECT COUNT(i) FROM ItemsEntities i WHERE i.sellerId.id= :sellerId";
	
	public static final String FIND_ITEMS_COUNT_BY_WINNER= "SELECT COUNT(i) FROM ItemsEntities i WHERE i.winnerId.id= :winnerId";
	
	public static final String FIND_ITEMS_COUNT_SOLD_BY_USER= "SELECT COUNT(i) FROM ItemsEntities i WHERE i.sellerId.id= :sellerId AND i.status= 'CLOSED' AND winnerId IS NOT NULL";
	
}
