package com.biddingapp.repositories;

import java.util.List;

import org.apache.openjpa.util.UserException;

import com.biddingapp.entities.UserEntity;

public interface UserDetailsRepository {

	public List<UserEntity> findAllUsers() throws UserException;
}
