package com.biddingapp.items;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.biddingapp.entities.ItemsEntities;
import com.biddingapp.entities.UserEntity;
import com.biddingapp.repositories.ItemsRepository;
import com.biddingapp.repositories.UserRepository;
import com.fortech.exception.ItemsDetailsException;

@Stateless
public class ItemsService {

	@EJB
	ItemsRepository itemsRepository;
	UserRepository userRepository;

	public ItemsRepository getItemsRepository() {
		return itemsRepository;
	}

	public void setItemsRepository(ItemsRepository itemsRepository) {
		this.itemsRepository = itemsRepository;
	}

	
	public List<ItemsEntities> getItems(UserEntity user) throws ItemsDetailsException{
		return itemsRepository.findEntitiesbyUser(user);
	}
	
	
	public UserEntity getUser(int id){
		return userRepository.findUserbyID(id);
	}
}