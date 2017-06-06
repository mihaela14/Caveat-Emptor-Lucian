package com.biddingapp.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.biddingapp.queries.AccountQueries;

@Entity
@NamedQuery(name="findAccountByUser", query= AccountQueries.FIND_ACCOUNT_BY_USER)
@Table(name="account")
public class AccountEntities implements Serializable{

	private static final long serialVersionUID = 2721316512177273695L;

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@OneToOne(cascade= CascadeType.MERGE, fetch = FetchType.EAGER)
	@JoinColumn(name="user_id")
	private UserEntity userId;

	@Column
	private String adress;

	@Column
	private int zipcode;

	@Column
	private String city;

	@Column
	private int phone;

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

	public String getAdress() {
		return adress;
	}
	public void setAdress(String adress) {
		this.adress = adress;
	}

	public int getZipcode() {
		return zipcode;
	}
	public void setZipcode(int zipcode) {
		this.zipcode = zipcode;
	}

	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public int getPhone() {
		return phone;
	}
	public void setPhone(int phone) {
		this.phone = phone;
	}

}
