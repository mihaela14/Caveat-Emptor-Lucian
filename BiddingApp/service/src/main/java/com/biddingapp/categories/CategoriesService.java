package com.biddingapp.categories;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.biddingapp.entities.CategoriesEntities;
import com.biddingapp.entities.UserEntity;
import com.biddingapp.repositories.CategoriesRepository;
import com.biddingapp.repositories.UserRepository;
import com.fortech.dto.CategoriesDTO;
import com.fortech.dto.UserDTO;
import com.google.gson.Gson;

@Stateless
public class CategoriesService {

	@EJB
	CategoriesRepository categoriesRepository;

	private static final String PERSISTENCE_UNIT_NAME = "BiddingApp";

	@PersistenceContext(unitName = PERSISTENCE_UNIT_NAME)
	private EntityManager entityManager;

	public EntityManager getEntityManager() {
		return entityManager;
	}
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}


	public CategoriesEntities getRoot(){
		return categoriesRepository.findCategorybyId(1);
	}


	public CategoriesDTO getDto(CategoriesEntities entity){

		CategoriesDTO dto= new CategoriesDTO();

		if(entity !=null){
			dto.setId(entity.getId());
			dto.setName(entity.getName());
			dto.setDescription(entity.getDescription());

			if(entity.getChildren() != null){
				List<CategoriesDTO> childListDto = new ArrayList<>();

				for(CategoriesEntities children : entity.getChildren()){
					CategoriesDTO childrenDto = getDto(children);
					childListDto.add(childrenDto);
				}
				dto.setNodes(childListDto);
			}
		}
		return dto;
	}


	public String getTreeStructure(){

		CategoriesDTO categoriesRoot= getDto(getRoot());	
		String jsonInput= new Gson().toJson(categoriesRoot);
		jsonInput=jsonInput.replaceAll(",\"nodes\":\\[\\]", "");

		return jsonInput;
	}

	public void deleteCategoryById(int id){
		if(id > 1){
			categoriesRepository.removeCategory(id);
		}
	}

	public void deleteCategory(String name){
		CategoriesEntities categoriesEntities= categoriesRepository.findCategorybyName(name);
		categoriesRepository.removeCategory(categoriesEntities.getId());
	}


	public void createCategory(CategoriesDTO categoriesDto){
		CategoriesEntities categoriesEntities= populate(categoriesDto);
		categoriesRepository.addCategory(categoriesEntities);
	}


	public CategoriesEntities populate(CategoriesDTO categoriesdto) {
		CategoriesEntities categoriesEntities = new CategoriesEntities();
		categoriesEntities.setName(categoriesdto.getName());
		categoriesEntities.setDescription(categoriesdto.getDescription());
		return categoriesEntities;
	}
}