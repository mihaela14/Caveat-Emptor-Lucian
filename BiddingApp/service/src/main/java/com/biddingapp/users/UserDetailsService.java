package com.biddingapp.users;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.openjpa.util.UserException;

import com.biddingapp.entities.UserEntity;
import com.biddingapp.repositories.UserDetailsRepository;
import com.biddingapp.repositories.UserRepository;
import com.fortech.exception.AccountDetailsException;

@Stateless
public class UserDetailsService {

	@EJB
	UserDetailsRepository detailsRepository;
	
	@EJB
	UserRepository userRepository;

	
	public List<UserEntity> getAllUsers() throws UserException{
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
}
