package com.biddingapp.ejbeans;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;

public interface UserRemote {

	public String loginUser();
	
}
