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
				dto.setChildren(childListDto);
			}
		}
		return dto;
	}


	public Tree getTreeDto(CategoriesDTO dto){
		Tree tree= new Tree();

		if(dto != null){
			tree.setText(dto.getName());

			if(dto.getChildren() != null){
				List<Tree> treeList = new ArrayList<>();

				for(CategoriesDTO children: dto.getChildren()){
					Tree treeChild= getTreeDto(children);
					treeList.add(treeChild);
				}
				tree.setNodes(treeList);
			}
		}
		return tree;
	}


	public String getTreeStructure(){

		CategoriesDTO categoriesRoot= getDto(getRoot());	
		Tree tree= getTreeDto(categoriesRoot);	
		String jsonInput= new Gson().toJson(tree);

		jsonInput=jsonInput.replaceAll(",\"nodes\":\\[\\]", "");

		return jsonInput;
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