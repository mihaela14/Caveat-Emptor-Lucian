package com.biddingapp.queries;

public class ItemsQueries {

	public static final String FIND_ITEM_BY_USER= "SELECT i FROM ItemsEntities i WHERE i.sellerId = :sellerId";
}
