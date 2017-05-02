package com.biddingapp.repositories;
import com.biddingapp.entities.RegistrationEntities;
import com.biddingapp.entities.UserEntity;
import com.fortech.exception.AccountDetailsException;

public interface UserRepository {

	public void addUser(UserEntity entity);	
	
	public void updateUser(UserEntity entity);
	
	public void removeUser(int id);
	
	public UserEntity findUserbyID(int id);
	
	public UserEntity findAllUsersWithName(String accountName) throws AccountDetailsException;
	
	public void addRegistration(RegistrationEntities registrationEntity);
	
	public void removeRegistration(int id);
	
	public RegistrationEntities getUserByActivationKey(String key) throws AccountDetailsException;	
	
	public boolean getStatusbyUsername(String accountName) throws AccountDetailsException;

	public UserEntity findAllUsersByEmail(String email) throws AccountDetailsException;	
	
}
