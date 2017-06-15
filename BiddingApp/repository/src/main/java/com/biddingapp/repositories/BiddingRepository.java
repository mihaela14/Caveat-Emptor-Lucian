package com.biddingapp.repositories;

import java.util.List;

import com.biddingapp.entities.BiddingEntities;
import com.biddingapp.entities.CategoriesEntities;
import com.biddingapp.entities.ItemsEntities;
import com.biddingapp.entities.UserEntity;
import com.fortech.exception.BiddingOperationsException;


public interface BiddingRepository {

	public void addBid(BiddingEntities bid);
	
	public void removeBidById(int id);
	
	public void removeBidbyEntity(BiddingEntities bid);
	
	public void updateBid(BiddingEntities bid);
	
	public List<ItemsEntities> findBidsByCategory(CategoriesEntities category) throws BiddingOperationsException;
	
	public List<ItemsEntities> findBidsByCategoryId(int categoryId) throws BiddingOperationsException;
	
	public List<BiddingEntities> findBidsByUser(UserEntity user) throws BiddingOperationsException;

	public BiddingEntities findBidByItemUser(int userId, int itemId) throws BiddingOperationsException;
	
	public BiddingEntities findbidById(int id);
	
	public Long getBidsNumber(int itemId) throws BiddingOperationsException;
	
	public Float findMaxBid(int itemId) throws BiddingOperationsException;

	public List<BiddingEntities> findBidsByItem(ItemsEntities item) throws BiddingOperationsException;
	
}
