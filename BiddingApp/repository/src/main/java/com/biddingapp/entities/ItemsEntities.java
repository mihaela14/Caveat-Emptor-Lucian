package com.biddingapp.entities;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;

import com.biddingapp.queries.UserQueries;

@NamedQuery(name="findItemByUser", query= UserQueries.FIND_BY_USERNAME)
public class ItemsEntities {
	
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column
	private String name;
	
	@Column(name="category_id")
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="category_id")
	private CategoriesEntities category;
	
	@Column
	private float price;
	
	@Column(name="best_id")
	private float bestBid;
	
	@Column
	private int bids;
	
	@Column(name="opening_date")
	private Timestamp openingDate;
	
	@Column(name="closing_date")
	private Timestamp closingDate;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="seller_id")
	private UserEntity sellerId;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="winner_id")
	private UserEntity winnerId;

	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public CategoriesEntities getCategory() {
		return category;
	}
	public void setCategory(CategoriesEntities category) {
		this.category = category;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public float getBestBid() {
		return bestBid;
	}
	public void setBestBid(float bestBid) {
		this.bestBid = bestBid;
	}
	public int getBids() {
		return bids;
	}
	public void setBids(int bids) {
		this.bids = bids;
	}
	public Timestamp getOpeningDate() {
		return openingDate;
	}
	public void setOpeningDate(Timestamp openingDate) {
		this.openingDate = openingDate;
	}
	public Timestamp getClosingDate() {
		return closingDate;
	}
	public void setClosingDate(Timestamp closingDate) {
		this.closingDate = closingDate;
	}
	public UserEntity getSellerId() {
		return sellerId;
	}
	public void setSellerId(UserEntity sellerId) {
		this.sellerId = sellerId;
	}
	public UserEntity getWinnerId() {
		return winnerId;
	}
	public void setWinnerId(UserEntity winnerId) {
		this.winnerId = winnerId;
	}
	
	

}
