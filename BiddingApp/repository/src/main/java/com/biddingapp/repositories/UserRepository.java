package com.biddingapp.repositories;

import java.util.List;

import com.biddingapp.entities.RegistrationEntities;
import com.biddingapp.entities.UserEntity;
import com.fortech.dto.RegistrationDTO;
import com.fortech.dto.UserDTO;

public interface UserRepository {

	public void addUser(RegistrationEntities entity);
	public void updateUser(RegistrationDTO entity);
	public void removeUser(int id);
	public UserEntity findRegistrationbyID(int id);
	public UserEntity findUserbyID(int id);
	public boolean isValidUser(String accountName);	
	public boolean isValidPassword(String password, String accountName);
	public int getIdByActivationKey(String key);
	
	
//TODO public List<T> getAll();
}
