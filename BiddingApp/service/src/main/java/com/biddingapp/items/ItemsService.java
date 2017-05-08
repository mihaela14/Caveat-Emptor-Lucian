package com.biddingapp.items;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.biddingapp.entities.ItemsEntities;
import com.biddingapp.entities.UserEntity;
import com.biddingapp.repositories.ItemsRepository;
import com.biddingapp.repositories.UserRepository;
import com.fortech.exception.AccountDetailsException;
import com.fortech.exception.ItemsDetailsException;

@Stateless
public class ItemsService {

	@EJB
	ItemsRepository itemsRepository;
	
	@EJB
	UserRepository userRepository;
	

	public List<ItemsEntities> getItemList(String accountName) throws AccountDetailsException, ItemsDetailsException {
		UserEntity user = userRepository.findAllUsersWithName(accountName);
		
		return itemsRepository.findItemsbyUser(user);		
	
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
}