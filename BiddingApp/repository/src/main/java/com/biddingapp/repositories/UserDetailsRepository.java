package com.biddingapp.repositories;

import java.util.List;

import org.apache.openjpa.util.UserException;

import com.biddingapp.entities.UserEntity;
import com.fortech.exception.ItemsDetailsException;
import com.fortech.exception.UserDetailsException;

public interface UserDetailsRepository {

	public List<UserEntity> findAllUsers() throws UserDetailsException;
	
	public Long findNumberOfItemsPlaced(int sellerId);

	public Long findNumberOfItemsSold(int sellerId);

	public Long findNumberOfItemsBought(int sellerId);
}
