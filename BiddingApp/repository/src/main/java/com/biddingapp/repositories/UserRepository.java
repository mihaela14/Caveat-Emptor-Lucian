package com.biddingapp.repositories;

import java.util.List;

import com.biddingapp.entities.UserEntity;

public interface UserRepository<T> {

	public void add(final T entity);
	public void update(final T entity);
	public void remove(int id);
	public UserEntity findbyID(int id);
	public boolean isValidUser(String accountName);	
	public boolean isValidPassword(String password, String accountName);
	
//TODO public List<T> getAll();
}
