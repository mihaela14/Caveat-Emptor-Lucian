package com.biddingapp.ejbeans;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import com.biddingapp.bidding.BiddingService;
import com.biddingapp.entities.BiddingEntities;
import com.biddingapp.entities.ItemsEntities;
import com.biddingapp.items.ItemsService;
import com.biddingapp.items.utilities.ItemTableSorting;
import com.fortech.dto.BidItemsDTO;
import com.fortech.dto.ItemsDTO;
import com.fortech.exception.AccountDetailsException;
import com.fortech.exception.BiddingOperationsException;
import com.fortech.exception.ItemsDetailsException;
import com.fortech.utils.Constants;
import com.fortech.utils.ItemStatus;

@ManagedBean(name ="items")
@SessionScoped
public class ItemsBean {

	@EJB
	private ItemsService itemsService;

	@EJB
	private BiddingService biddingService;

	@ManagedProperty(value = "#{login}")
	private UserLoginBean userDetails;

	private ItemsDTO itemDto;

	private List<ItemsEntities> itemsList;

	private List<ItemsDTO> DTOList;

	private List<BidItemsDTO> buyItemsDTOList;

	private int categoryId;

	private boolean itemToBuyOrSale;


	@PostConstruct
	public void init() {
		itemDto = new ItemsDTO();
		DTOList= populateDTOList();
		setBuyItemsDTOList(populateItemsToBuyDTOList());
	}


	public String editAction(ItemsDTO item){
		item.setEditable(true);
		return null;
	}


	public void changeToBuy(){
		itemToBuyOrSale=true;
		init();
	}


	public void changeToSell(){
		itemToBuyOrSale=false;
		init();
	}


	public String cancelAction(ItemsDTO item){
		item.setEditable(false);
		return null;
	}


	public String saveAction() {
		for (ItemsDTO items : DTOList){
			itemsService.updateItem(getUpdateEntity(items));
			items.setEditable(false);
		}
		return null;

	}

	public ItemsEntities getUpdateEntity(ItemsDTO items){
		ItemsEntities itemEntity= new ItemsEntities();

		Timestamp opening= stringToTimestampUpdater(items.getOpeningDate());
		Timestamp closing= stringToTimestampUpdater(items.getClosingDate());

		itemEntity.setId(items.getId());
		itemEntity.setName(items.getName());
		
		Float price= Float.parseFloat(String.format("%.2f",items.getPrice()));
		itemEntity.setPrice(price);
		
		itemEntity.setCategory(itemsService.getCategory(items.getCategoryId()));
		itemEntity.setOpeningDate(opening);
		itemEntity.setClosingDate(closing);
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


	public List<BidItemsDTO> populateItemsToBuyDTOList(){
		List<BiddingEntities> bids= new ArrayList<>();

		try {
			bids= biddingService.getBidsByUsername(userDetails.getAccountName());
			buyItemsDTOList= new ArrayList<>();

			for (BiddingEntities bid : bids) {
				BidItemsDTO bidItemsDTO = getItemsToBuyTableDto(bid);
				buyItemsDTOList.add(bidItemsDTO);
			}
			return getBuyItemsDTOList();

		} catch (AccountDetailsException | BiddingOperationsException e) {
			e.printStackTrace();
			return null;
		}
	}


	private BidItemsDTO getItemsToBuyTableDto(BiddingEntities bid) {
		BidItemsDTO biddingDto= new BidItemsDTO();

		biddingDto.setBidValue(bid.getBidValue());
		biddingDto.setBidDate(bid.getDate());

		ItemsEntities item= new ItemsEntities();
		item= biddingService.getItemsbyId(bid.getItemId().getId());

		biddingDto.setName(item.getName());
		biddingDto.setClosingDate(item.getClosingDate());
		biddingDto.setPrice(item.getPrice());
		biddingDto.setStatus(item.getStatus());

		return biddingDto;
	}

	public ItemsDTO getTableDto(ItemsEntities item){
		ItemsDTO createDto= new ItemsDTO();

		String opening= item.getOpeningDate().toString() ;
		String closing= item.getClosingDate().toString();
		int totalBids=0;
		float bestBid=0.0f;


		try{
			totalBids = itemsService.getTotalBids(item.getId());
		} catch (BiddingOperationsException boe) {
			totalBids=0;
			boe.printStackTrace();
		}


		if(totalBids>0){
			try {
				bestBid= itemsService.getMaxBid(item.getId());
			} catch (BiddingOperationsException e) {
				e.printStackTrace();
			}
		}


		createDto.setId(item.getId());
		createDto.setName(item.getName());
		createDto.setCategoryName(item.getCategory().getName());
		createDto.setCategoryId(item.getCategory().getId());
		createDto.setPrice(item.getPrice());
		createDto.setBestBid(bestBid);
		createDto.setBids(totalBids);
		createDto.setOpeningDate(opening);
		createDto.setClosingDate(closing);
		createDto.setStatus(item.getStatus());
		createDto.setSellerId(item.getSellerId().getId());

		if(item.getWinnerId() != null){
			createDto.setWinner(item.getWinnerId().getAccountName());
		}
		createDto.setEditable(false);				
		return createDto;
	}


	public ItemsEntities getDto(){
		ItemsEntities itemEntity= new ItemsEntities();

		Timestamp opening= stringToTimestamp(itemDto.getOpeningDate());
		Timestamp closing= stringToTimestamp(itemDto.getClosingDate());
		Timestamp currentTimespamp= getCurentTimestamp();

		itemEntity.setName(itemDto.getName());
		
		Float price= Float.parseFloat(String.format("%.2f",itemDto.getPrice()));
		itemEntity.setPrice(price);
		
		itemEntity.setOpeningDate(opening);
		itemEntity.setClosingDate(closing);

		if(closing.before(currentTimespamp)){
			itemEntity.setStatus(ItemStatus.CLOSED.name());
		}else if(opening.after(currentTimespamp)){
			itemEntity.setStatus(ItemStatus.NOT_YET_OPENED.getValue());
		}else
			itemEntity.setStatus(ItemStatus.CLOSED.name());

		itemEntity.setCategory(itemsService.getCategory(categoryId));
		itemEntity.setSellerId(itemsService.getSellerIdByUsername(userDetails.getAccountName()));
		itemEntity.setDescription(itemDto.getDescription());
		itemEntity.setImage(itemDto.getImage());

		return itemEntity;
	}


	public void addItem(){
		itemsService.createItem(getDto());
		init();
	}


	public Timestamp stringToTimestamp(String datetime){

		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
			Date parsedDate = dateFormat.parse(datetime);
			Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
			return timestamp;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}


	public Timestamp stringToTimestampUpdater(String datetime){

		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("MM/DD/YYYY HH:mm:SS a");
			Date parsedDate = dateFormat.parse(datetime);
			Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
			return timestamp;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Timestamp getCurentTimestamp(){
		try{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String datetime= sdf.format(new Timestamp(System.currentTimeMillis()));
			Date parsedDate = sdf.parse(datetime);
			Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
			return timestamp;
		}catch(ParseException e){
			e.printStackTrace();
		}
		return null;
	}



	public void IsPriceValid(FacesContext context, UIComponent componentToValidate, Object value){
		Float price= (Float)value;

		if(price<=0){
			FacesMessage message= new FacesMessage("*Price cannot be less than 0");
			throw new ValidatorException(message);
		}
	}


	public boolean editDisabled(String status){
		if(status.equals("CLOSED")){
			return true;
		}else{
			return false;
		}
	}

		public void sortByName(){
			ItemTableSorting.sortTableByName(DTOList);
		}

		public void sortByOpeningDate(){
			ItemTableSorting.sortbyOpeningDate(DTOList);
		}

		public void sortByClosingDate(){
			ItemTableSorting.sortbyClosingDate(DTOList);
		}

		public void sortByStatus(){
			ItemTableSorting.sortByStatus(DTOList);
		}

		public void sortByCategory(){
			ItemTableSorting.sortByCategory(DTOList);
		}

		public void sortByPrice(){
			ItemTableSorting.sortByPrice(DTOList);
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

		public List<BidItemsDTO> getBuyItemsDTOList() {
			return buyItemsDTOList;
		}

		public void setBuyItemsDTOList(List<BidItemsDTO> buyItemsDTOList) {
			this.buyItemsDTOList = buyItemsDTOList;
		}

		public boolean isItemToBuyOrSale() {
			return itemToBuyOrSale;
		}

		public void setItemToBuyOrSale(boolean itemToBuyOrSale) {
			this.itemToBuyOrSale = itemToBuyOrSale;
		}
	}
