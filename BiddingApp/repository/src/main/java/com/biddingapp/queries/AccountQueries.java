package com.biddingapp.queries;

public class AccountQueries {

	public static final String FIND_ACCOUNT_BY_USER= "SELECT i FROM AccountEntities i WHERE i.userId.id = :userId";
	
}
