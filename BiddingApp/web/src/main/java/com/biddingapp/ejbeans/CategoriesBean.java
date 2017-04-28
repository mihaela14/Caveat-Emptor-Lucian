package com.biddingapp.ejbeans;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.persistence.Column;

import com.biddingapp.categories.CategoriesService;
import com.biddingapp.register.RegisterValidation;

@ManagedBean(name = "categories")
@SessionScoped
public class CategoriesBean {

	private int id;
	private String name;
	private String description;
	
	@ManagedProperty(value = "treeInput")
	private String treeInput;

	@EJB
	private CategoriesService categoriesService;
	
	
	public String getTreeInput() {
		setTreeInput(getJsonInput());
		return treeInput;
	}
	public void setTreeInput(String treeInput) {
		this.treeInput = treeInput;
	}
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
	public CategoriesService getCategoriesService() {
		return categoriesService;
	}
	public void setCategoriesService(CategoriesService categoriesService) {
		this.categoriesService = categoriesService;
	}
		
	public String getJsonInput(){
		String script= categoriesService.getTreeStructure();
		return script;
	}
}

