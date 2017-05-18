package com.biddingapp.bidding;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.biddingapp.entities.BiddingEntities;
import com.biddingapp.entities.CategoriesEntities;
import com.biddingapp.entities.ItemsEntities;
import com.biddingapp.repositories.BiddingRepository;
import com.biddingapp.repositories.CategoriesRepository;
import com.biddingapp.repositories.ItemsRepository;
import com.fortech.exception.BiddingOperationsException;

@Stateless
public class BiddingService {

	@EJB
	BiddingRepository biddingRepository;
	
	@EJB
	CategoriesRepository categoryRepository;


	public BiddingRepository getBiddingRepository() {
		return biddingRepository;
	}
	public void setBiddingRepository(BiddingRepository biddingRepository) {
		this.biddingRepository = biddingRepository;
	}

	public List<ItemsEntities> getItems(CategoriesEntities category) throws BiddingOperationsException {
		List<ItemsEntities> itemsList;
		itemsList= biddingRepository.findBidsByCategory(category);
		return itemsList;
	}
	
	public CategoriesEntities getCategory(int id){
		return categoryRepository.findCategorybyId(id);
	}
}
