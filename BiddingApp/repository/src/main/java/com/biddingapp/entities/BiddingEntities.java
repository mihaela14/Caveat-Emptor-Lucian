package com.biddingapp.entities;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.biddingapp.queries.ItemsQueries;
import com.biddingapp.queries.UserQueries;

@Entity
@NamedQuery(name="findBidsByCategory", query= ItemsQueries.FIND_ITEM_BY_CATEGORY)
@Table(name="bidding")
public class BiddingEntities {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@OneToOne(cascade= CascadeType.PERSIST)
	@JoinColumn(name="user_id")
	private UserEntity userId;
	
	@OneToOne(cascade= CascadeType.PERSIST)
	@JoinColumn(name="item_id")
	private ItemsEntities itemId;
	
	@Column(name="value")
	private Float bidValue;
	
	@Column(name="date")
	private Timestamp date;

	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public UserEntity getUserId() {
		return userId;
	}
	public void setUserId(UserEntity userId) {
		this.userId = userId;
	}
	public ItemsEntities getItemId() {
		return itemId;
	}
	public void setItemId(ItemsEntities itemId) {
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

}
