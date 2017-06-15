package com.biddingapp.ejbeans;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.biddingapp.categories.CategoriesService;
import com.biddingapp.items.ItemsService;
import com.fortech.dto.CategoriesDTO;
import com.fortech.exception.BiddingOperationsException;
import com.fortech.exception.CategoriesDetailsException;
import com.fortech.exception.ItemsDetailsException;

@ManagedBean(name = "categoriesOperation")
@SessionScoped
public class CategoriesOperationsBean {

	private int id;
	private String name;
	private String description;

	@EJB
	private CategoriesService categoriesService;
	
	@EJB
	private ItemsService itemService;


	public void removeCategory(){
		try {
			itemService.deleteItemsWithCategory(id);
			categoriesService.deleteCategory(id);
		} catch (CategoriesDetailsException | ItemsDetailsException | BiddingOperationsException e) {
			e.getMessage();
			e.printStackTrace();
		}
	}


	public String addCategory(){
		CategoriesDTO dto= getDto();
		categoriesService.createCategory(dto, id);
		return "main-page"+ "?faces-redirect=true&amp;includeViewParams=true";
	}

	public String addChild(){
		CategoriesDTO dto= getDto();
		categoriesService.createChild(dto, id);
		return "main-page"+ "?faces-redirect=true&amp;includeViewParams=true";
	}

	public String updateChild(){
		CategoriesDTO dto= getDto();
		categoriesService.updateChild(dto, id);
		return "main-page"+ "?faces-redirect=true&amp;includeViewParams=true";
	}


	public CategoriesDTO getDto() {
		CategoriesDTO createDto= new CategoriesDTO();
		createDto.setName(name);
		createDto.setDescription(description);
		return createDto;
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
}