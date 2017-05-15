package com.biddingapp.ejbeans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.event.AjaxBehaviorEvent;

import com.biddingapp.entities.ItemsEntities;
import com.biddingapp.items.ItemsService;
import com.biddingapp.items.utilities.ItemTableSorting;
import com.fortech.dto.ItemsDTO;
import com.fortech.exception.AccountDetailsException;
import com.fortech.exception.ItemsDetailsException;

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

	private int first;

	private boolean previousVisible;

	private boolean nextVisible;


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
			itemsService.updateItem(getUpdateEntity(items));
			items.setEditable(false);
		}
		//return to current page
		return null;

	}

	public ItemsEntities getUpdateEntity(ItemsDTO items){
		ItemsEntities itemEntity= new ItemsEntities();

		itemEntity.setId(items.getId());
		itemEntity.setName(items.getName());
		itemEntity.setPrice(items.getPrice());

		//TODO category tree to get categoryId
		itemEntity.setCategory(itemsService.getCategory(items.getCategoryId()));

		itemEntity.setBestBid(items.getBestBid());
		itemEntity.setBids(items.getBids());
		itemEntity.setOpeningDate(items.getOpeningDate());
		itemEntity.setClosingDate(items.getClosingDate());
		itemEntity.setStatus(items.getStatus());
		itemEntity.setWinnerId(itemsService.getUserUsingId(items.getWinnerId()));
		itemEntity.setSellerId(itemsService.getUserUsingId(items.getSellerId()));

		return itemEntity;
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
		createDto.setCategoryId(item.getCategory().getId());
		createDto.setPrice(item.getPrice());
		createDto.setBestBid(item.getBestBid());
		createDto.setBids(item.getBids());
		createDto.setOpeningDate(item.getOpeningDate());
		createDto.setClosingDate(item.getClosingDate());
		createDto.setStatus(item.getStatus());
		createDto.setSellerId(item.getSellerId().getId());
		//		createDto.setWinnerId(item.getWinnerId().getId());

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
		itemEntity.setDescription(itemDto.getDescription());

		return itemEntity;
	}

	public void addItem(){
		itemsService.createItem(getDto());
		init();
	}


	public String sortByName(){
		ItemTableSorting.sortTableByName(DTOList);
		return null;
	}


	public String next() {
		first = first + 5;
		if (first > DTOList.size()) {
			first = DTOList.size() - 5;
		}
		return null;
	}


	public String previous() {
		first = first - 5;
		if (first <= 0) {
			first = 1;
		}
		return null;
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

	public int getFirst() {
		return first;
	}

	public void setFirst(int first) {
		this.first = first;
	}

	public boolean isPreviousVisible() {
		return previousVisible;
	}

	public void setPreviousVisible(boolean previousVisible) {
		this.previousVisible = previousVisible;
	}

	public boolean isNextVisible() {
		return nextVisible;
	}

	public void setNextVisible(boolean nextVisible) {
		this.nextVisible = nextVisible;
	}
}
