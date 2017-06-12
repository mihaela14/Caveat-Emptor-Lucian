package com.biddingapp.ejbeans;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import com.biddingapp.bidding.BiddingService;
import com.biddingapp.categories.CategoriesService;
import com.biddingapp.entities.BiddingEntities;
import com.biddingapp.entities.CategoriesEntities;
import com.biddingapp.entities.ItemsEntities;
import com.biddingapp.items.ItemsService;
import com.fortech.dto.BiddingDTO;
import com.fortech.dto.CategoriesDTO;
import com.fortech.dto.ItemsDTO;
import com.fortech.exception.BiddingOperationsException;

@ManagedBean(name = "bidding")
@ViewScoped
public class BiddingBean {

	@EJB
	private BiddingService biddingService;

	@EJB
	private CategoriesService categoryService;
	
	@EJB
	private ItemsService itemsService;

	private BiddingDTO biddingDTO;

	private CategoriesDTO categoriesDTO;

	private List<ItemsDTO> ItemsDTOList;

	private List<ItemsEntities> itemsList;

	private int categoryId;

	private ItemsDTO itemForModal;

	private boolean hasBid = true;

	private BiddingEntities bid;

	
	@PostConstruct
	public void init() {
		biddingDTO = new BiddingDTO();
		categoriesDTO = new CategoriesDTO();
	}

	
	public String getCategoryName() {
		if (categoryId != 0) {
			return biddingService.getCategory(categoryId).getName();
		} else {
			return "Category's description";
		}
	}

	
	public String getCategoryDescription() {
		if (categoryId != 0) {
			return biddingService.getCategory(categoryId).getDescription();
		} else {
			return "Description";
		}
	}

	
	public void getItemTablerows() {
		itemsList = new ArrayList<>();
		ItemsDTOList = populateChildrenList(categoryId);
	}

	
	public List<ItemsDTO> populateChildrenList(int id) {
		try {
			CategoriesEntities currentCategory = categoryService.getCategory(id);
			List<CategoriesEntities> children = currentCategory.getChildren();
			ItemsDTOList = new ArrayList<>();
			itemsList.addAll(biddingService.getItems(id));

			if (children.isEmpty()) {
				for (ItemsEntities item : itemsList) {

					ItemsDTO itemDTO = getTableDto(item);
					ItemsDTOList.add(itemDTO);
				}
				return ItemsDTOList;
			} else {
				for (CategoriesEntities child : children) {
					populateChildrenList(child.getId());
				}
			}
			return ItemsDTOList;
		} catch (BiddingOperationsException boe) {
			boe.printStackTrace();
			return null;
		}
	}


	public void populateBid() {
		biddingDTO.setItemId(itemForModal.getId());
		biddingDTO.setUserId(itemForModal.getSellerId());
	}


	public void submitBid() {
		BiddingEntities bidEntity= new BiddingEntities();
		bidEntity= getBiddingEntity(biddingDTO);
		
		Float valueOfBid= Float.parseFloat(String.format("%.2f",bidEntity.getBidValue()));		
		bidEntity.setBidValue(valueOfBid);
		
		biddingService.bid(bidEntity);
		hasBid = true;
	}


	public void removeBid() {
		biddingService.remove(getBiddingEntity(biddingDTO));
		hasBid = false;
	}


	public void editBid() {
		hasBid = false;
		biddingDTO.setPreviousBid(biddingDTO.getBidValue());
		biddingDTO.setBidValue(null);
	}


	public void initialHasBid() {
		
		populateBid();
		BiddingEntities bid = biddingService.getEntityWithUserItem(getBiddingEntity(biddingDTO));
		if (bid != null) {
			setDateValuetoBid(bid);
			hasBid = true;
		} else {
			hasBid = false;
			biddingDTO.setBidValue(null);
		}
	}


	public void setDateValuetoBid(BiddingEntities bid){
		biddingDTO.setBidValue(bid.getBidValue());
		biddingDTO.setDate(bid.getDate());
	}


	public BiddingEntities getBiddingEntity(BiddingDTO dto) {
		BiddingEntities bid = new BiddingEntities();
		bid.setBidValue(dto.getBidValue());
		bid.setItemId(biddingService.getItemsbyId(dto.getItemId()));
		bid.setUserId(biddingService.getUserbyId(dto.getUserId()));
		bid.setDate((new Timestamp(System.currentTimeMillis())));
		return bid;
	}


	private ItemsDTO getTableDto(ItemsEntities item) {
		ItemsDTO createDto = new ItemsDTO();

		String opening = item.getOpeningDate().toString();
		String closing = item.getClosingDate().toString();
		int totalBids=0;
		float bestBid=0.0f;
		
		try{
			totalBids = getItemsService().getTotalBids(item.getId());
		} catch (BiddingOperationsException boe) {
			totalBids=0;
			boe.printStackTrace();
		}

		if(totalBids>0){
			try {
				bestBid= getItemsService().getMaxBid(item.getId());
			} catch (BiddingOperationsException e) {
				e.printStackTrace();
			}
		}

		createDto.setId(item.getId());
		createDto.setName(item.getName());
		createDto.setCategoryName(item.getCategory().getName());
		createDto.setCategoryId(item.getCategory().getId());
		createDto.setDescription(item.getDescription());
		createDto.setPrice(item.getPrice());
		createDto.setBestBid(bestBid);
		createDto.setBids(totalBids);
		createDto.setOpeningDate(opening);
		createDto.setClosingDate(closing);
		createDto.setStatus(item.getStatus());
		createDto.setSellerId(item.getSellerId().getId());
		createDto.setImage(item.getImage());

		if (item.getWinnerId() != null) {
			createDto.setWinner(item.getWinnerId().getAccountName());
		}
		return createDto;
	}	
	

	public void IsBidValid(FacesContext context, UIComponent componentToValidate, Object value){
		Float bid= (Float)value;

		if(bid<=0){
			FacesMessage message= new FacesMessage("*Bid cannot be less or equal to 0");
			throw new ValidatorException(message);
		}
	}


	public void getIdForUser(int id) {
		biddingDTO.setUserId(id);
	}


	public void getIdForItem(int id) {
		biddingDTO.setItemId(id);
	}


	public BiddingService getBiddingService() {
		return biddingService;
	}
	public void setBiddingService(BiddingService biddingService) {
		this.biddingService = biddingService;
	}

	public BiddingDTO getBiddingDTO() {
		return biddingDTO;
	}
	public void setBiddingDTO(BiddingDTO biddingDTO) {
		this.biddingDTO = biddingDTO;
	}
	
	public CategoriesService getCategoryService() {
		return categoryService;
	}
	public void setCategoryService(CategoriesService categoryService) {
		this.categoryService = categoryService;
	}

	public List<ItemsDTO> getItemsDTOList() {
		return ItemsDTOList;
	}
	public void setItemsDTOList(List<ItemsDTO> itemsDTOList) {
		ItemsDTOList = itemsDTOList;
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
	public CategoriesDTO getCategoriesDTO() {
		return categoriesDTO;
	}

	public void setCategoriesDTO(CategoriesDTO categoriesDTO) {
		this.categoriesDTO = categoriesDTO;
	}
	public ItemsDTO getItemForModal() {
		return itemForModal;
	}

	public void setItemForModal(ItemsDTO itemForModal) {
		this.itemForModal = itemForModal;
	}

	public boolean isHasBid() {
		return hasBid;
	}
	public void setHasBid(boolean hasBid) {
		this.hasBid = hasBid;
	}

	public BiddingEntities getBid() {
		return bid;
	}
	public void setBid(BiddingEntities bid) {
		this.bid = bid;
	}


	public ItemsService getItemsService() {
		return itemsService;
	}


	public void setItemsService(ItemsService itemsService) {
		this.itemsService = itemsService;
	}
}
