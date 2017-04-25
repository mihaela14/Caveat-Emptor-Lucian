package com.biddingapp.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
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

	@Column(name="parent_id", updatable= false, insertable= false)
	private int parentId;
	
	@ManyToOne
	private CategoriesEntities parent;

	@OneToMany(mappedBy="parent")
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
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
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
		this.parentId = parentId;
	}
	public CategoriesEntities() {
		super();
	}

}
