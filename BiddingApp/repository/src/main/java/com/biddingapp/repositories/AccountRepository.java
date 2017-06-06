package com.biddingapp.repositories;

import com.biddingapp.entities.AccountEntities;
import com.fortech.exception.AccountDetailsException;

public interface AccountRepository {

	public AccountEntities findAccountForUser(int userId) throws AccountDetailsException;

	public void updateAccount(AccountEntities account);
}
