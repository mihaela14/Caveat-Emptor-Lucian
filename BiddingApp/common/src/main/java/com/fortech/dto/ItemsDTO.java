package com.fortech.dto;

import java.sql.Timestamp;

public class ItemsDTO {


	private int id;

	private String name;

	private float price;
	
	private String categoryName;

	private float bestBid;

	private int bids;

	private Timestamp openingDate;

	private Timestamp closingDate;
	
	private String status;
	
	private String seller;
	
	private String winner;
	
	boolean editable;


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
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getSeller() {
		return seller;
	}
	public void setSeller(String seller) {
		this.seller = seller;
	}
	public String getWinner() {
		return winner;
	}
	public void setWinner(String winner) {
		this.winner = winner;
	}
	public boolean isEditable() {
		return editable;
	}
	public void setEditable(boolean editable) {
		this.editable = editable;
	}

}
