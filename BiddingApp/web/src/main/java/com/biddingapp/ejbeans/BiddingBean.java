package com.biddingapp.ejbeans;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.biddingapp.bidding.BiddingService;
import com.biddingapp.categories.CategoriesService;
import com.biddingapp.entities.BiddingEntities;
import com.biddingapp.entities.CategoriesEntities;
import com.biddingapp.entities.ItemsEntities;
import com.fortech.dto.BiddingDTO;
import com.fortech.dto.CategoriesDTO;
import com.fortech.dto.ItemsDTO;
import com.fortech.exception.BiddingOperationsException;
import com.fortech.exception.CategoriesDetailsException;
import com.fortech.utils.Constants;

@ManagedBean(name = "bidding")
@ViewScoped
public class BiddingBean {

	@EJB
	private BiddingService biddingService;

	@EJB
	private CategoriesService categoryService;

	private BiddingDTO biddingDTO;

	private CategoriesDTO categoriesDTO;

	private List<ItemsDTO> DTOList;

	private List<ItemsEntities> itemsList;

	private int categoryId;

	private ItemsDTO itemForModal;

	private Timestamp timestamp;

	private boolean hasBid = true;

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
		DTOList = populateChildrenList(categoryId);
	}

	public List<ItemsDTO> populateChildrenList(int id) {
		try {
			CategoriesEntities currentCategory = categoryService.getCategory(id);
			List<CategoriesEntities> children = currentCategory.getChildren();
			DTOList = new ArrayList<>();

			itemsList.addAll(biddingService.getItems(id));

			if (children.isEmpty()) {
				for (ItemsEntities item : itemsList) {

					ItemsDTO itemDTO = getTableDto(item);
					DTOList.add(itemDTO);
				}
				return DTOList;
			} else {
				for (CategoriesEntities child : children) {
					populateChildrenList(child.getId());
				}
			}
			return DTOList;

		} catch (BiddingOperationsException boe) {
			boe.printStackTrace();
			return null;
		}

	}

	public void populateBid() {
		biddingDTO.setItemId(itemForModal.getId());
		biddingDTO.setUserId(itemForModal.getSellerId());
		biddingDTO.setDate(new Timestamp(System.currentTimeMillis()));

	}

	public void submitBid() {
		populateBid();
		biddingService.bid(getBiddingEntity(biddingDTO));
		hasBid = true;
	}

	public void removeBid() {
		populateBid();
		biddingService.remove(getBiddingEntity(biddingDTO));
		hasBid = false;
	}

	public void editBid() {
		hasBid = false;
	}

	public void initialHasBid() {
		populateBid();
		BiddingEntities bid = biddingService.getEntityWithUserItem(getBiddingEntity(biddingDTO));
		if (bid != null) {
			hasBid = true;
		} else {
			hasBid = false;
		}
	}

	public BiddingEntities getBiddingEntity(BiddingDTO dto) {
		BiddingEntities bid = new BiddingEntities();
		bid.setBidValue(biddingDTO.getBidValue());
		bid.setItemId(biddingService.getItemsbyId(dto.getItemId()));
		bid.setUserId(biddingService.getUserbyId(dto.getUserId()));
		bid.setDate(biddingDTO.getDate());
		return bid;
	}

	public void getIdForUser(int id) {
		biddingDTO.setUserId(id);
	}

	public void getIdForItem(int id) {
		biddingDTO.setItemId(id);
	}

	private ItemsDTO getTableDto(ItemsEntities item) {
		ItemsDTO createDto = new ItemsDTO();

		String opening = item.getOpeningDate().toString();
		String closing = item.getClosingDate().toString();

		createDto.setId(item.getId());
		createDto.setName(item.getName());
		createDto.setCategoryName(item.getCategory().getName());
		createDto.setCategoryId(item.getCategory().getId());
		createDto.setDescription(item.getDescription());
		createDto.setPrice(item.getPrice());
		createDto.setBestBid(item.getBestBid());
		createDto.setBids(item.getBids());
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

	public List<ItemsDTO> getDTOList() {
		return DTOList;
	}

	public void setDTOList(List<ItemsDTO> dTOList) {
		DTOList = dTOList;
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
}
