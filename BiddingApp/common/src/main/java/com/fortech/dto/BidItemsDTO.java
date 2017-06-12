package com.fortech.dto;

import java.sql.Timestamp;

public class BidItemsDTO {

	private String name;
	
	private Float price;
	
	private Float bidValue;
	
	private Float maxBid;
	
	private int bids;
	
	private String status;
	
	private Timestamp bidDate;
	
	private Timestamp closingDate;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Float getPrice() {
		return price;
	}
	public void setPrice(Float price) {
		this.price = price;
	}
	public Float getBidValue() {
		return bidValue;
	}
	public void setBidValue(Float bidValue) {
		this.bidValue = bidValue;
	}
	public Float getMaxBid() {
		return maxBid;
	}
	public void setMaxBid(Float maxBid) {
		this.maxBid = maxBid;
	}
	public int getBids() {
		return bids;
	}
	public void setBids(int bids) {
		this.bids = bids;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Timestamp getBidDate() {
		return bidDate;
	}
	public void setBidDate(Timestamp bidDate) {
		this.bidDate = bidDate;
	}
	public Timestamp getClosingDate() {
		return closingDate;
	}
	public void setClosingDate(Timestamp closingDate) {
		this.closingDate = closingDate;
	}
}
