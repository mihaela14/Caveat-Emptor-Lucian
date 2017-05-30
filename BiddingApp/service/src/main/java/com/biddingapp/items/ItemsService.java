package com.biddingapp.items;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.biddingapp.entities.CategoriesEntities;
import com.biddingapp.entities.ItemsEntities;
import com.biddingapp.entities.UserEntity;
import com.biddingapp.repositories.BiddingRepository;
import com.biddingapp.repositories.CategoriesRepository;
import com.biddingapp.repositories.ItemsRepository;
import com.biddingapp.repositories.UserRepository;
import com.fortech.exception.AccountDetailsException;
import com.fortech.exception.BiddingOperationsException;
import com.fortech.exception.ItemsDetailsException;

@Stateless
public class ItemsService {

	@EJB
	ItemsRepository itemsRepository;
	
	@EJB
	UserRepository userRepository;
	
	@EJB
	CategoriesRepository categoriesRepository;
	
	@EJB
	BiddingRepository biddingRepository;
	

	public List<ItemsEntities> getItemList(String accountName) throws AccountDetailsException, ItemsDetailsException {
		UserEntity user = userRepository.findAllUsersWithName(accountName);
		
		return itemsRepository.findItemsbyUser(user);		
	
	}
	
	public void createItem(ItemsEntities itemsEntity){
		itemsRepository.add(itemsEntity);
	}
	
	public void updateItem(ItemsEntities itemsEntity){
		itemsRepository.updateItem(itemsEntity);
	}
	
	public CategoriesEntities getCategory(int categoryId) {
		return categoriesRepository.findCategorybyId(categoryId);
	}
	
	public UserEntity getSellerIdByUsername(String accountName){	
		try {
			return userRepository.findAllUsersWithName(accountName);
		} catch (AccountDetailsException e) {
			e.printStackTrace();
			return null;
		}	
	}
	
	
	public int getTotalBids(int itemId) throws BiddingOperationsException{
		return biddingRepository.getBidsNumber(itemId).intValue();
	}
	
	
	public Float getMaxBid(int itemId) throws BiddingOperationsException{
		return biddingRepository.findMaxBid(itemId);
	}
	
	
	public UserEntity getUserUsingId(int id){
		return userRepository.findUserbyID(id);
	}
	
	
	public ItemsRepository getItemsRepository() {
		return itemsRepository;
	}
	public void setItemsRepository(ItemsRepository itemsRepository) {
		this.itemsRepository = itemsRepository;
	}
	
	public UserRepository getUserRepository() {
		return userRepository;
	}
	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	public CategoriesRepository getCategoriesRepository() {
		return categoriesRepository;
	}
	public void setCategoriesRepository(CategoriesRepository categoriesRepository) {
		this.categoriesRepository = categoriesRepository;
	}
	public BiddingRepository getBiddingRepository() {
		return biddingRepository;
	}
	public void setBiddingRepository(BiddingRepository biddingRepository) {
		this.biddingRepository = biddingRepository;
	}
	
}