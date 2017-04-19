package com.biddingapp.utilities;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.biddingapp.entities.Roles;
import com.biddingapp.entities.UserEntity;

public class UserUtilities {

	  private EntityManagerFactory factory = Persistence.createEntityManagerFactory("BiddingApp");
	  private EntityManager manager = factory.createEntityManager();
	  private EntityTransaction transaction = manager.getTransaction();
	
	public UserEntity createUser(int id, String firstName, String lastName, String email, String accountName, String password, String roles){
		UserEntity user= new UserEntity();
		user.setId(id);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setEmail(email);
		user.setAccountName(accountName);
		user.setPassword(password);
		user.setRoles(roles);
		
		transaction.begin();
		manager.persist(user);
		transaction.commit();
		
		return user;
	}
	
	public void removeUser(int id){
		UserEntity user= manager.find(UserEntity.class, id);
		if(user != null){
			transaction.begin();
			manager.remove(user);
			transaction.commit();
		}
	}
	
	public UserEntity findUser(int id){
		return manager.find(UserEntity.class, id);
	}
}
