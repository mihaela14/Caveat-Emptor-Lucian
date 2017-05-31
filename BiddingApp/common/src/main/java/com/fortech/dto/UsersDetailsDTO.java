package com.fortech.dto;

public class UsersDetailsDTO {
	
	private int id;

	private String name;

	private String accountName;

	private String email;

	private boolean isAdmin;

	private boolean isEnabled;

	private int itemsPlaced;

	private int itemsSold;

	private int itemsBought;

	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isAdmin() {
		return isAdmin;
	}
	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public boolean isEnabled() {
		return isEnabled;
	}
	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	public int getItemsPlaced() {
		return itemsPlaced;
	}
	public void setItemsPlaced(int itemsPlaced) {
		this.itemsPlaced = itemsPlaced;
	}

	public int getItemsSold() {
		return itemsSold;
	}
	public void setItemsSold(int itemsSold) {
		this.itemsSold = itemsSold;
	}

	public int getItemsBought() {
		return itemsBought;
	}
	public void setItemsBought(int itemsBought) {
		this.itemsBought = itemsBought;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}
