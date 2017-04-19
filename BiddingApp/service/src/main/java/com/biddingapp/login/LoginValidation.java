package com.biddingapp.login;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.biddingapp.repositories.UserRepository;

@Stateless
public class LoginValidation {

	@EJB
	private UserRepository userRepository;

	private static final String PERSISTENCE_UNIT_NAME = "BiddingApp";

	@PersistenceContext(unitName= PERSISTENCE_UNIT_NAME)
	private EntityManager entityManager;

	public boolean validateUsernamePassword(String accountName, String password) {
		boolean validUser = getUserRepository().isValidUser(accountName);
		boolean validPassword = getUserRepository().isValidPassword(password, accountName);

		if (validUser && validPassword) {
			return true;
		} else
			return false;
	}

	public UserRepository getUserRepository() {
		return userRepository;
	}

	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
}
