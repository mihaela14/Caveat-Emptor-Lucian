//package com.biddingapp.login;
//
//import javax.ejb.Remote;
//import javax.ejb.Stateless;
//import javax.persistence.EntityManager;
//import javax.persistence.NoResultException;
//import javax.persistence.PersistenceContext;
//import javax.persistence.PersistenceContextType;
//
//import com.biddingapp.entities.UserEntity;
//
//@Stateless
//@Remote(LoginValidation.class)
//public class LoginValidationImplementation {
//
//	private static final String PERSISTENCE_UNIT_NAME = "BiddingApp";
//
//	@PersistenceContext(unitName= PERSISTENCE_UNIT_NAME)
//	private EntityManager entityManager;
//
//	public boolean isValidUser(String accountName){
//		try{
//			UserEntity userEntity= (UserEntity) entityManager.createNamedQuery("findAllUsersWithName").setParameter("accountName", accountName).getSingleResult();
//			if(userEntity.getAccountName().equals(accountName)){
//				return true;
//			}
//		}catch(NoResultException nre){
//			return false;
//		}
//		return false;
//	}
//
//	public boolean isValidPassword(String password, String accountName){
//		try{
//			UserEntity userEntity= (UserEntity) entityManager.createNamedQuery("findAllUsersWithName").setParameter("accountName", accountName).getSingleResult();
//			if(userEntity.getPassword().equals(password)){
//				return true;
//			}
//		}catch(NoResultException nre){
//			return false;
//		}
//		return false;
//	}
//
//	public EntityManager getEntityManager() {
//		return entityManager;
//	}
//
//	public void setEntityManager(EntityManager entityManager) {
//		this.entityManager = entityManager;
//	}
//}
