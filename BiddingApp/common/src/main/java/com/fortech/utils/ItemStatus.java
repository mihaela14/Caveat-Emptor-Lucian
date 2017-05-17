package com.fortech.utils;

public enum ItemStatus {

	OPEN,
	CLOSED,
	ABANDONED,
	NOT_YET_OPENED("NOT YET OPENED");

	private String value;    

	private ItemStatus(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	private ItemStatus() {
	}

}
