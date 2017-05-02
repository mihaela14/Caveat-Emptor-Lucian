package com.biddingapp.ejbeans;

import java.io.IOException;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.biddingapp.categories.CategoriesService;
import com.fortech.dto.CategoriesDTO;
import com.fortech.dto.UserDTO;

@ManagedBean(name = "categoriesOperation")
@SessionScoped
public class CategoriesOperationsBean {

	private int id;
	private String name;
	private String description;

	@EJB
	private CategoriesService categoriesService;
	
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
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public CategoriesService getCategoriesService() {
		return categoriesService;
	}
	public void setCategoriesService(CategoriesService categoriesService) {
		this.categoriesService = categoriesService;
	}
	
	public void removeCategory(){
		categoriesService.deleteCategory(id);
	}
	
	public String addCategory(){
		CategoriesDTO dto= getDto();
		categoriesService.createCategory(dto, id);
		return "main-page";
	}
	
	public CategoriesDTO getDto() {
		CategoriesDTO createDto= new CategoriesDTO();
		createDto.setName(name);
		createDto.setDescription(description);
		return createDto;
	}
}

