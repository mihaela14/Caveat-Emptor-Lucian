package com.biddingapp.queries;

public class UserQueries {

	public static final String FIND_BY_ACTIVATION_KEY = "SELECT u FROM RegistrationEntities u WHERE u.activationKey = :activationKey";
	
	public static final String FIND_BY_CATEGORY_NAME = "SELECT u FROM CategoriesEntities u WHERE u.name = :name";
	
	public static final String FIND_BY_USERNAME = "SELECT u FROM UserEntity u WHERE u.accountName = :accountName";
	
	public static final String FIND_BY_EMAIL = "SELECT u FROM UserEntity u WHERE u.email = :email";
	
	public static final String FIND_ALL_USERS = "SELECT u FROM UserEntity u";
	
}
