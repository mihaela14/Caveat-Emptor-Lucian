package com.biddingapp.ejbeans;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.biddingapp.entities.UserEntity;
import com.biddingapp.items.ItemsService;
import com.fortech.dto.ItemsDTO;
import com.fortech.exception.ItemsDetailsException;

@ManagedBean(name ="items")
@SessionScoped
public class ItemsBean {

	@EJB
	private ItemsService itemsService;

	private ItemsDTO itemDto;

	@PostConstruct
	public void init() {
		itemDto = new ItemsDTO();
	}
	
	
	public ItemsService getItemsService() {
		return itemsService;
	}
	public void setItemsService(ItemsService itemsService) {
		this.itemsService = itemsService;
	}
	public ItemsDTO getItemDto() {
		return itemDto;
	}
	public void setItemDto(ItemsDTO itemDto) {
		this.itemDto = itemDto;
	}

	public void addItem(){
		UserEntity user= itemsService.getUser(itemDto.getId());
		try {
			itemsService.getItems(user);
		} catch (ItemsDetailsException e) {
			e.printStackTrace();
			FacesMessage message= new FacesMessage("User items not valid !");
		}
	}
}
