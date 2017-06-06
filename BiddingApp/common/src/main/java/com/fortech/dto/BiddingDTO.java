package com.fortech.dto;

import java.sql.Timestamp;

public class BiddingDTO {

	private int userId;

	private int itemId;

	private Float bidValue;

	private Timestamp date;
	
	private Float previousBid;

	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public Float getBidValue() {
		return bidValue;
	}
	public void setBidValue(Float bidValue) {
		this.bidValue = bidValue;
	}
	public Timestamp getDate() {
		return date;
	}
	public void setDate(Timestamp date) {
		this.date = date;
	}
	public Float getPreviousBid() {
		return previousBid;
	}
	public void setPreviousBid(Float previousBid) {
		this.previousBid = previousBid;
	}

}
