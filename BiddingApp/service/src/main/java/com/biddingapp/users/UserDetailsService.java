package com.biddingapp.users;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.biddingapp.entities.UserEntity;
import com.biddingapp.repositories.ItemsRepository;
import com.biddingapp.repositories.UserDetailsRepository;
import com.biddingapp.repositories.UserRepository;
import com.fortech.dto.UserDTO;
import com.fortech.exception.AccountDetailsException;
import com.fortech.exception.ItemsDetailsException;
import com.fortech.exception.UserDetailsException;

@Stateless
public class UserDetailsService {

	@EJB
	UserDetailsRepository detailsRepository;

	@EJB
	UserRepository userRepository;

	@EJB
	ItemsRepository itemsRepository;


	public List<UserEntity> getAllUsers() throws UserDetailsException{
		return detailsRepository.findAllUsers();
	}


	public boolean isUserAdmin(String accountName) throws AccountDetailsException{
		UserEntity user= userRepository.findAllUsersWithName(accountName);
		if(user.getRoles().equals("ADMIN")){
			return true;
		}else{
			return false;
		}
	}


	public void updateUser(UserEntity userEntity) {
		userRepository.updateUser(userEntity);
	}


	public UserEntity findUser(int userId) {
		return userRepository.findUserbyID(userId);
	}


	public int getCountOfItemsPlaced(int sellerId){
		Long itemsPlaced= detailsRepository.findNumberOfItemsPlaced(sellerId);
		return itemsPlaced.intValue();
	}


	public int getCountOfItemsSold(int sellerId) {
		Long itemsPlaced= detailsRepository.findNumberOfItemsSold(sellerId);
		return itemsPlaced.intValue();
	}

	
	public int getCountofItemsBought(int winnerId) {
		Long itemsPlaced= detailsRepository.findNumberOfItemsBought(winnerId);
		return itemsPlaced.intValue();
	}
}
