package com.biddingapp.repositories;

import java.util.List;

import com.biddingapp.entities.ItemsEntities;
import com.biddingapp.entities.UserEntity;
import com.fortech.exception.ItemsDetailsException;

public interface ItemsRepository {
	
	public void add(ItemsEntities itemsEntities);
	public void remove(int id);
	public void updateItem(ItemsEntities itemsEntities);
	public List<ItemsEntities> findItemsbyUser(UserEntity user) throws ItemsDetailsException;
	public ItemsEntities findItemById(int id);
	public List<ItemsEntities> findItemsbyCategory(int id) throws ItemsDetailsException;

}
