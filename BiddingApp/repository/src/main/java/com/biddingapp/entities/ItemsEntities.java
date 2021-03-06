package com.biddingapp.entities;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.biddingapp.queries.ItemsQueries;

@Entity
@NamedQueries({
	@NamedQuery(name="findItemByUser", query= ItemsQueries.FIND_ITEM_BY_USER),
	@NamedQuery(name="findItemByCategory", query= ItemsQueries.FIND_ITEM_BY_CATEGORY),
	@NamedQuery(name="findItemsCountByUser", query= ItemsQueries.FIND_ITEMS_COUNT_BY_USER),
	@NamedQuery(name="findItemsCountByWinner", query= ItemsQueries.FIND_ITEMS_COUNT_BY_WINNER),
	@NamedQuery(name="findItemsCountSoldByUser", query= ItemsQueries.FIND_ITEMS_COUNT_SOLD_BY_USER)
})
@Table(name= "items")
public class ItemsEntities implements Serializable{

	private static final long serialVersionUID = 5913721175475853574L;

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column
	private String name;

	@ManyToOne
	@JoinColumn(name="category_id", referencedColumnName = "id")
	private CategoriesEntities category;

	@Column
	private float price;

	@Column(name="opening_date")
	private Timestamp openingDate;

	@Column(name="closing_date")
	private Timestamp closingDate;

	@Column
	private String status;

	@ManyToOne
	@JoinColumn(name="seller_id", referencedColumnName = "ID")
	private UserEntity sellerId;

	@ManyToOne
	@JoinColumn(name="winner_id", referencedColumnName = "ID")
	private UserEntity winnerId;

	@Column
	private String description; 

	@Column
	private String image;
	
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}



}
