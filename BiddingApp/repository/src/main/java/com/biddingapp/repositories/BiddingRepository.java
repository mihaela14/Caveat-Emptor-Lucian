package com.biddingapp.repositories;

import java.util.List;

import com.biddingapp.entities.BiddingEntities;
import com.biddingapp.entities.CategoriesEntities;
import com.biddingapp.entities.ItemsEntities;
import com.fortech.exception.BiddingOperationsException;


public interface BiddingRepository {

	public void addBid(BiddingEntities bid);
	
	public void removeBidById(int id);
	
	public void removeBidbyEntity(BiddingEntities bid);
	
	public void updateBid(BiddingEntities bid);
	
	public BiddingEntities findBidsByUser();
	
	public List<ItemsEntities> findBidsByCategory(CategoriesEntities category) throws BiddingOperationsException;
	
	public List<ItemsEntities> findBidsByCategoryId(int categoryId) throws BiddingOperationsException;
	
}
