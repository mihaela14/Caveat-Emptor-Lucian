package com.biddingapp.ejbeans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

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

	private List<ItemsEntities> itemsList;

	private List<ItemsDTO> DTOList;

	private int categoryId;


	@PostConstruct
	public void init() {
		itemDto = new ItemsDTO();
		DTOList= populateDTOList();
	}
	
	public String editAction(ItemsDTO item){
		item.setEditable(true);
		return null;
	}
	
	
	public String saveAction() {
		//get all existing value but set "editable" to false
		for (ItemsDTO items : DTOList){
			items.setEditable(false);
			
			//TODO update on save itemsService.updateItem(getDto());
		}
		//return to current page
		return null;

	}

	
	public List<ItemsDTO> populateDTOList(){
		try {
			itemsList= itemsService.getItemList(userDetails.getAccountName());	
			DTOList= new ArrayList<>();

			for (ItemsEntities item : itemsList) {
				ItemsDTO itemDTO = getTableDto(item);
				DTOList.add(itemDTO);
			}
			return DTOList;
		} catch (AccountDetailsException | ItemsDetailsException e) {
			e.printStackTrace();
			return null;
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
		
		createDto.setEditable(false);				
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

	public List<ItemsEntities> getItemsList() {
		return itemsList;
	}
	public void setItemsList(List<ItemsEntities> itemsList) {
		this.itemsList = itemsList;
	}

	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public List<ItemsDTO> getDTOList() {
		return DTOList;
	}
	public void setDTOList(List<ItemsDTO> dTOList) {
		DTOList = dTOList;
	}
}
