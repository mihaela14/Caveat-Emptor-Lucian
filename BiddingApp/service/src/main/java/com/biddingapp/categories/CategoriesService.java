package com.biddingapp.categories;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.biddingapp.entities.CategoriesEntities;
import com.biddingapp.repositories.CategoriesRepository;
import com.fortech.exception.CategoriesDetailsException;
import com.google.gson.Gson;

import dto.CategoriesDTO;

@Stateless
public class CategoriesService {

	@EJB
	CategoriesRepository categoriesRepository;


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

	public void deleteCategory(int id) throws CategoriesDetailsException{
		if(id > 1){
			categoriesRepository.removeCategory(id);
		}
	}

	public void createCategory(CategoriesDTO categoriesdto, int id) {

		CategoriesEntities category= populate(categoriesdto);

		if(id==0){
			CategoriesEntities root= categoriesRepository.findCategorybyId(1);
			category.setParent(root);
			categoriesRepository.addCategory(category);
		}else{
			CategoriesEntities selected= categoriesRepository.findCategorybyId(id);
			if(selected.getName().equals(categoriesdto.getName())){
				selected.setDescription(categoriesdto.getDescription());
				categoriesRepository.updateCategory(selected);
			}else{
				category.setParent(selected);
				categoriesRepository.addCategory(category);
			}
		}
		categoriesdto= null;
	}

	public CategoriesEntities populate(CategoriesDTO categoriesdto){
		CategoriesEntities categoriesEntities = new CategoriesEntities();

		categoriesEntities.setName(categoriesdto.getName());
		categoriesEntities.setDescription(categoriesdto.getDescription());

		return categoriesEntities;
	}
}