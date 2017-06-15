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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.biddingapp.queries.BiddingQueries;
import com.biddingapp.queries.ItemsQueries;

@Entity
@NamedQueries({
	@NamedQuery(name="findBidsByCategory", query= ItemsQueries.FIND_ITEM_BY_CATEGORY),
	@NamedQuery(name="findBidByItemUser", query= BiddingQueries.FIND_BID_BY_ITEM_USER),
	@NamedQuery(name="findBidById", query= BiddingQueries.FIND_BID_BY_ID),
	@NamedQuery(name="findBidsByUser", query= BiddingQueries.FIND_BIDS_BY_USER),
	@NamedQuery(name="countBidsByItem", query= BiddingQueries.COUNT_BID),
	@NamedQuery(name="maxBidByItem", query= BiddingQueries.MAX_BID),
	@NamedQuery(name="findBidsByItem", query= BiddingQueries.FIND_BIDS_BY_ITEM)
})
@Table(name="bidding")
public class BiddingEntities implements Serializable{
	private static final long serialVersionUID = 1215211368970850173L;

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@OneToOne(cascade= CascadeType.MERGE, fetch = FetchType.EAGER)
	@JoinColumn(name="user_id")
	private UserEntity userId;

	@OneToOne(cascade= CascadeType.MERGE, fetch = FetchType.EAGER)
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
