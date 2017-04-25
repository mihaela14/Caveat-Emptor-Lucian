package com.biddingapp.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@NamedQuery(name="findCategorybyName", query= "SELECT u FROM CategoriesEntities u WHERE u.name = :name")
@Table(name="categories")
public class CategoriesEntities implements Serializable{

	private static final long serialVersionUID = -4526595782465607986L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String name;

	private String description;
	
	@ManyToOne
	private CategoriesEntities parent;

	@OneToMany(mappedBy="parent", fetch=FetchType.EAGER)
	private List<CategoriesEntities>children;

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
	public List<CategoriesEntities> getChildren() {
		return children;
	}
	public void setChildren(List<CategoriesEntities> children) {
		this.children = children;
	}

	public CategoriesEntities(int id, String name, String description, int parentId) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
	}
	public CategoriesEntities() {
		super();
	}

}
