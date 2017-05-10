package com.biddingapp.entities;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.biddingapp.queries.ItemsQueries;

@Entity
@NamedQuery(name="findItemByUser", query= ItemsQueries.FIND_ITEM_BY_USER)
@Table(name= "items")
public class ItemsEntities implements Serializable{
	
	private static final long serialVersionUID = 5913721175475853574L;

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column
	private String name;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="category_id", referencedColumnName = "id")
	private CategoriesEntities category;
	
	@Column
	private float price;
	
	@Column(name="best_bid")
	private float bestBid;
	
	@Column
	private int bids;
	
	@Column(name="opening_date")
	private Timestamp openingDate;
	
	@Column(name="closing_date")
	private Timestamp closingDate;
	
	@Column
	private String status;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="seller_id", referencedColumnName = "ID")
	private UserEntity sellerId;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="winner_id", referencedColumnName = "ID")
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
