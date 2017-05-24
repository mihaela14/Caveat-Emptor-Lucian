package com.biddingapp.bidding;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.biddingapp.entities.BiddingEntities;
import com.biddingapp.entities.CategoriesEntities;
import com.biddingapp.entities.ItemsEntities;
import com.biddingapp.entities.UserEntity;
import com.biddingapp.repositories.BiddingRepository;
import com.biddingapp.repositories.CategoriesRepository;
import com.biddingapp.repositories.ItemsRepository;
import com.biddingapp.repositories.UserRepository;
import com.fortech.exception.BiddingOperationsException;

@Stateless
public class BiddingService {

	@EJB
	BiddingRepository biddingRepository;
	
	@EJB
	CategoriesRepository categoryRepository;
	
	@EJB
	ItemsRepository itemsRepository;
	
	@EJB
	UserRepository userRepository;
	
	
	public List<ItemsEntities> getItems(int categoryId) throws BiddingOperationsException{
		return biddingRepository.findBidsByCategoryId(categoryId);
	}
	
	
	public CategoriesEntities getCategory(int id){
		return categoryRepository.findCategorybyId(id);
	}
	
	
	public void bid(BiddingEntities bid){
		biddingRepository.addBid(bid);
	}
	
	public ItemsEntities getItemsbyId(int id){
		return itemsRepository.findItemById(id);
	}
	
	
	public UserEntity getUserbyId(int id){
		return userRepository.findUserbyID(id);
	}
	
	
	public BiddingRepository getBiddingRepository() {
		return biddingRepository;
	}
	public void setBiddingRepository(BiddingRepository biddingRepository) {
		this.biddingRepository = biddingRepository;
	}
}
