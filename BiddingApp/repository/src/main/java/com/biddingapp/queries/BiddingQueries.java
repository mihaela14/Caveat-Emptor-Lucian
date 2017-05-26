package com.biddingapp.queries;

public class BiddingQueries {

	public static final String FIND_BID_BY_ITEM_USER= "SELECT b FROM BiddingEntities b WHERE b.userId.id = :userId AND b.itemId.id= :itemId";
	public static final String FIND_BID_BY_ID= "SELECT b FROM BiddingEntities b WHERE b.id= :id";

}
