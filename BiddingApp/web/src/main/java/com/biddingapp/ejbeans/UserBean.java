package com.biddingapp.ejbeans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.apache.openjpa.util.UserException;

import com.biddingapp.entities.UserEntity;
import com.biddingapp.register.RegisterValidation;
import com.biddingapp.users.UserDetailsService;
import com.fortech.dto.UserDTO;
import com.fortech.dto.UsersDetailsDTO;
import com.fortech.exception.AccountDetailsException;
import com.fortech.exception.UserDetailsException;

@ManagedBean(name = "userBean")
@SessionScoped
public class UserBean {

	@EJB
	private UserDetailsService userDetailsService;

	@ManagedProperty(value = "#{login}")
	private UserLoginBean userDetails;	

	private UsersDetailsDTO userDTO;

	private List<UserEntity> userList;

	private List<UsersDetailsDTO> userDTOList;
	
	private boolean isLoggedUserAuthorized;

	@PostConstruct
	public void init() {
		userDTO = new UsersDetailsDTO();
		userDTOList= populateUserDTOList();
		getAuthorization();
	}


	public void getAuthorization(){
		try {
			isLoggedUserAuthorized= userDetailsService.isUserAdmin(userDetails.getAccountName());
		} catch (AccountDetailsException e) {
			isLoggedUserAuthorized= false;
			e.printStackTrace();
		}
	}
	
	
	public void disableUser(int userId){
		UserEntity userEntity= new UserEntity();		
		userEntity= userDetailsService.findUser(userId);
		userEntity.setStatus(false);	
		userDetailsService.updateUser(userEntity);
		init();	
	}

	
	public void enableUser(int userId){
		UserEntity userEntity= new UserEntity();		
		userEntity= userDetailsService.findUser(userId);
		userEntity.setStatus(true);	
		userDetailsService.updateUser(userEntity);
		init();	
	}
	
	
	public List<UsersDetailsDTO> populateUserDTOList(){
		try {
			userList= userDetailsService.getAllUsers();
			userDTOList= new ArrayList<>();

			for(UserEntity user: userList){
				UsersDetailsDTO UsersDetailsDTO = getTableDto(user);
				userDTOList.add(UsersDetailsDTO);
			}
			return userDTOList;
		} catch (UserDetailsException ude) {
			ude.printStackTrace();
			return null;
		}
	}


	private UsersDetailsDTO getTableDto(UserEntity user) {
		UsersDetailsDTO createDto= new UsersDetailsDTO();

		createDto.setName(user.getFirstName()+" "+user.getLastName());
		createDto.setAccountName(user.getAccountName());
		createDto.setEmail(user.getEmail());

		if(user.getRoles().equals("ADMIN")){
			createDto.setAdmin(true);
		}else{
			createDto.setAdmin(false);
		}

		createDto.setEnabled(user.getStatus());
		createDto.setId(user.getId());
		createDto.setItemsPlaced(userDetailsService.getCountOfItemsPlaced(user.getId()));
		createDto.setItemsSold(userDetailsService.getCountOfItemsSold(user.getId()));	
		createDto.setItemsBought(userDetailsService.getCountofItemsBought(user.getId()));
		return createDto;
	}


	public UsersDetailsDTO getUserDTO() {
		return userDTO;
	}
	public void setUserDTO(UsersDetailsDTO userDTO) {
		this.userDTO = userDTO;
	}

	public UserDetailsService getUserDetailsService() {
		return userDetailsService;
	}
	public void setUserDetailsService(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}

	public UserLoginBean getUserDetails() {
		return userDetails;
	}
	public void setUserDetails(UserLoginBean userDetails) {
		this.userDetails = userDetails;
	}

	public List<UserEntity> getUserList() {
		return userList;
	}
	public void setUserList(List<UserEntity> userList) {
		this.userList = userList;
	}
	public List<UsersDetailsDTO> getUserDTOList() {
		return userDTOList;
	}
	public void setUserDTOList(List<UsersDetailsDTO> userDTOList) {
		this.userDTOList = userDTOList;
	}
	public boolean isLoggedUserAuthorized() {
		return isLoggedUserAuthorized;
	}
	public void setLoggedUserAuthorized(boolean isLoggedUserAuthorized) {
		this.isLoggedUserAuthorized = isLoggedUserAuthorized;
	}

}
