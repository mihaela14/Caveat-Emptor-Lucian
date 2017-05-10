package com.biddingapp.ejbeans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import com.biddingapp.entities.ItemsEntities;
import com.biddingapp.items.ItemsService;
import com.fortech.dto.ItemsDTO;
import com.fortech.exception.AccountDetailsException;
import com.fortech.exception.ItemsDetailsException;
import com.google.gson.Gson;

@ManagedBean(name ="items")
@SessionScoped
public class ItemsBean {

	@EJB
	private ItemsService itemsService;

	@ManagedProperty(value = "#{login}")
	private UserLoginBean userDetails;

	private ItemsDTO itemDto;

	private List<ItemsEntities> items;

	private int categoryId;

	private String json;

	@PostConstruct
	public void init() {
		Gson gson = new Gson();
		itemDto = new ItemsDTO();

		try {
			items= itemsService.getItemList(userDetails.getAccountName());	
			List<ItemsDTO> DTOList = new ArrayList<>();

			for (ItemsEntities item : items) {
				ItemsDTO itemDTO = getTableDto(item);
				DTOList.add(itemDTO);
			}

			setJson(gson.toJson(DTOList));

		} catch (AccountDetailsException | ItemsDetailsException e) {
			e.printStackTrace();
		}
	}

	public ItemsDTO getTableDto(ItemsEntities item){
		ItemsDTO createDto= new ItemsDTO();

		createDto.setId(item.getId());
		createDto.setName(item.getName());
		createDto.setCategoryName(item.getCategory().getName());
		createDto.setPrice(item.getPrice());
		createDto.setBestBid(item.getBestBid());
		createDto.setBids(item.getBids());
		createDto.setOpeningDate(item.getOpeningDate());
		createDto.setClosingDate(item.getClosingDate());
		createDto.setStatus(item.getStatus());

		if(item.getWinnerId() != null){
			createDto.setWinner(item.getWinnerId().getAccountName());
		}

		return createDto;
	}


	public ItemsEntities getDto(){
		ItemsEntities itemEntity= new ItemsEntities();

		itemEntity.setName(itemDto.getName());
		itemEntity.setPrice(itemDto.getPrice());
		itemEntity.setOpeningDate(itemDto.getOpeningDate());
		itemEntity.setClosingDate(itemDto.getClosingDate());
		itemEntity.setStatus(itemDto.getStatus());
		itemEntity.setCategory(itemsService.getCategory(categoryId));
		itemEntity.setSellerId(itemsService.getSellerIdByUsername(userDetails.getAccountName()));


		return itemEntity;
	}


	public void addItem(){
		itemsService.createItem(getDto());
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

	public UserLoginBean getUserDetails() {
		return userDetails;
	}
	public void setUserDetails(UserLoginBean userDetails) {
		this.userDetails = userDetails;
	}

	public List<ItemsEntities> getItems() {
		return items;
	}
	public void setItems(List<ItemsEntities> items) {
		this.items = items;
	}

	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getJson() {
		return json;
	}

	public void setJson(String json) {
		this.json = json;
	}

}
