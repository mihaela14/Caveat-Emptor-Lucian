package com.biddingapp.utilities;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.biddingapp.entities.RegistrationEntities;

public class RegistrationUtilities {

	private EntityManagerFactory factory= Persistence.createEntityManagerFactory("BiddingApp");
	private EntityManager manager= factory.createEntityManager();
	private EntityTransaction transaction = manager.getTransaction();
	
	public RegistrationEntities createRegistration(int id, long activationExpiry, boolean isActivated){
		RegistrationEntities registration= new RegistrationEntities();
		
		transaction.begin();
		manager.persist(registration);
		transaction.commit();
		
		return registration;
	}
	
	public void removeRegistration(int id){
		RegistrationEntities registration= manager.find(RegistrationEntities.class, id);
		if(registration != null){
			transaction.begin();
			manager.remove(registration);
			transaction.commit();
		}
	}
	
	public RegistrationEntities findRegistration(int id){
		return manager.find(RegistrationEntities.class, id);
	}
}
