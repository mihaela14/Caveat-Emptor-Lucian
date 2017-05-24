package com.biddingapp.ejbeans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.biddingapp.bidding.BiddingService;
import com.biddingapp.categories.CategoriesService;
import com.biddingapp.entities.CategoriesEntities;
import com.biddingapp.entities.ItemsEntities;
import com.fortech.dto.BiddingDTO;
import com.fortech.dto.CategoriesDTO;
import com.fortech.dto.ItemsDTO;
import com.fortech.exception.BiddingOperationsException;

@ManagedBean(name ="bidding")
@ViewScoped
public class BiddingBean {

	@EJB
	private BiddingService biddingService;

	@EJB
	private CategoriesService categoryService;

	private BiddingDTO biddingDTO;

	private CategoriesDTO categoriesDTO;

	private List<ItemsDTO> DTOList;

	private List<ItemsEntities> itemsList= new ArrayList<>();

	private int categoryId;

	private ItemsDTO itemForModal;

	@PostConstruct
	public void init() {
		biddingDTO= new BiddingDTO();
		categoriesDTO= new CategoriesDTO();
	}


	public String getCategoryName(){
		if(categoryId != 0){
			return biddingService.getCategory(categoryId).getName();
		}else{
			return "Category's description";
		}
	}


	public String getCategoryDescription(){
		if(categoryId != 0){
			return biddingService.getCategory(categoryId).getDescription();
		}else{
			return "Description";
		}
	}


	public void getItemTablerows(){
		DTOList= populateChildrenList(categoryId);
	}

	
	public void getDataForModal(ItemsDTO item){		
		setItemForModal(item);
	}
	
	
	public List<ItemsDTO> populateChildrenList(int id){
		try {		
			CategoriesEntities currentCategory= categoryService.getCategory(id);
			List<CategoriesEntities> children = currentCategory.getChildren();
			DTOList= new ArrayList<>();
			
			itemsList.addAll(biddingService.getItems(id));			

			if(children.isEmpty()){
				for (ItemsEntities item : itemsList) {

					ItemsDTO itemDTO = getTableDto(item);
					DTOList.add(itemDTO);
				}
				return DTOList;
			}else{
				for(CategoriesEntities child: children){
					populateChildrenList(child.getId());
				}
			}
			return DTOList;		

		}catch (BiddingOperationsException boe) {
			boe.printStackTrace();
			return null;
		}

	}

	public void submit(){	
	}

	private ItemsDTO getTableDto(ItemsEntities item) {
		ItemsDTO createDto= new ItemsDTO();

		String opening= item.getOpeningDate().toString() ;
		String closing= item.getClosingDate().toString();

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
		//		createDto.setWinnerId(item.getWinnerId().getId());

		if(item.getWinnerId() != null){
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
}
