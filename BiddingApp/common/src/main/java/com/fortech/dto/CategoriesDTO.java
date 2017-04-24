package com.fortech.dto;

import java.io.Serializable;

public class CategoriesDTO implements Serializable{

	private static final long serialVersionUID = -4526595782465607986L;


	private int id;

	private String name;

	private String description;

	private int parentId;
	

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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}


	public CategoriesDTO(int id, String name, String description, int parentId) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.parentId = parentId;
	}
	public CategoriesDTO() {
		super();
	}

}
