package com.biddingapp.register;

import java.sql.Timestamp;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import com.biddingapp.entities.RegistrationEntities;
import com.biddingapp.entities.UserEntity;
import com.biddingapp.repositories.UserRepository;
import com.fortech.dto.RegistrationDTO;
import com.fortech.dto.UserDTO;
import com.fortech.exception.AccountDetailsException;

@Stateless
public class RegisterValidation {

	@EJB
	UserRepository userRepository;

	private static final String PERSISTENCE_UNIT_NAME = "BiddingApp";

	@PersistenceContext(unitName = PERSISTENCE_UNIT_NAME)
	private EntityManager entityManager;

	public static final long HOUR = 3600 * 1000;

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	private Timestamp getCurrentDatePlusOneDay() {
		return new Timestamp(System.currentTimeMillis() + 24 * HOUR);
	}


	public void registerUser(UserDTO userRegistrationDto, String key) {
		RegistrationEntities registration = new RegistrationEntities();

		registration.setActivationExpiry(getCurrentDatePlusOneDay());
		UserEntity user = populate(userRegistrationDto);
		registration.setUserid(user);
		registration.setActivationKey(key);
		userRepository.addRegistration(registration);
	}


	public UserEntity populate(UserDTO userdto) {
		UserEntity user = new UserEntity();
		user.setFirstName(userdto.getFirstName());
		user.setLastName(userdto.getLastName());
		user.setEmail(userdto.getEmail());
		user.setAccountName(userdto.getAccountName());
		user.setPassword(userdto.getPassword());
		user.setStatus(userdto.getStatus());
		user.setRoles(userdto.getRoles());
		return user;
	}


	public boolean activateUserByKey(String key) throws AccountDetailsException {
		
			RegistrationEntities findUserbyFK = userRepository.getUserByActivationKey(key);

			if (findUserbyFK != null) {
				findUserbyFK.getUserid().setStatus(true);
				userRepository.updateUser(findUserbyFK.getUserid());
				userRepository.removeRegistration(findUserbyFK.getId());
				return true;
			}
		return false;
	}



	public boolean isDuplicateUsername(String accountName) throws AccountDetailsException{
			userRepository.findAllUsersWithName(accountName);
			return true;			
	}
	
	public boolean isDuplicateEmail(String email) throws AccountDetailsException{
		userRepository.findAllUsersByEmail(email);
		return true;			
}
}
