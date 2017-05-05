package com.fortech.dto;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class ItemsDTO {


	private int id;

	private String name;

	private float price;

	private float bestBid;

	private int bids;

	private Timestamp openingDate;

	private Timestamp closingDate;


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



}
