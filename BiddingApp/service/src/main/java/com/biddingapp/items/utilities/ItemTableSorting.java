package com.biddingapp.items.utilities;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.fortech.dto.ItemsDTO;

public class ItemTableSorting {

	private static boolean sortDescending = true;


	public static String sortTableByName(List<ItemsDTO> DTOList){
		if(sortDescending){
			Collections.sort(DTOList, new Comparator<ItemsDTO>() {
				@Override
				public int compare(ItemsDTO o1, ItemsDTO o2) {
					return o1.getName().compareTo(o2.getName());
				}
			});
			sortDescending = false;
		}else{
			Collections.sort(DTOList, new Comparator<ItemsDTO>() {
				@Override
				public int compare(ItemsDTO o1, ItemsDTO o2) {
					return o2.getName().compareTo(o1.getName());
				}
			});
			sortDescending = true;
		}
		return null;
	}


	public static Timestamp sortbyOpeningDate(List<ItemsDTO> DTOList){
		if(sortDescending){
			Collections.sort(DTOList, new Comparator<ItemsDTO>() {
				@Override
				public int compare(ItemsDTO o1, ItemsDTO o2) {
					return o1.getOpeningDate().compareTo(o2.getOpeningDate());
				}
			});
			sortDescending = false;
		}else{
			Collections.sort(DTOList, new Comparator<ItemsDTO>() {
				@Override
				public int compare(ItemsDTO o1, ItemsDTO o2) {
					return o2.getOpeningDate().compareTo(o1.getOpeningDate());
				}
			});
			sortDescending = true;
		}
		return null;
	}


	public static Timestamp sortbyClosingDate(List<ItemsDTO> DTOList){
		if(sortDescending){
			Collections.sort(DTOList, new Comparator<ItemsDTO>() {
				@Override
				public int compare(ItemsDTO o1, ItemsDTO o2) {
					return o1.getClosingDate().compareTo(o2.getClosingDate());
				}
			});
			sortDescending = false;
		}else{
			Collections.sort(DTOList, new Comparator<ItemsDTO>() {
				@Override
				public int compare(ItemsDTO o1, ItemsDTO o2) {
					return o2.getClosingDate().compareTo(o1.getClosingDate());
				}
			});
			sortDescending = true;
		}
		return null;
	}

	public static String sortByStatus(List<ItemsDTO> DTOList){
		if(sortDescending){
			Collections.sort(DTOList, new Comparator<ItemsDTO>() {
				@Override
				public int compare(ItemsDTO o1, ItemsDTO o2) {
					return o1.getStatus().compareTo(o2.getStatus());
				}
			});
			sortDescending = false;
		}else{
			Collections.sort(DTOList, new Comparator<ItemsDTO>() {
				@Override
				public int compare(ItemsDTO o1, ItemsDTO o2) {
					return o2.getStatus().compareTo(o1.getStatus());
				}
			});
			sortDescending = true;
		}
		return null;
	}


	public static String sortByCategory(List<ItemsDTO> DTOList){
		if(sortDescending){
			Collections.sort(DTOList, new Comparator<ItemsDTO>() {
				@Override
				public int compare(ItemsDTO o1, ItemsDTO o2) {
					return o1.getCategoryName().compareTo(o2.getCategoryName());
				}
			});
			sortDescending = false;
		}else{
			Collections.sort(DTOList, new Comparator<ItemsDTO>() {
				@Override
				public int compare(ItemsDTO o1, ItemsDTO o2) {
					return o2.getCategoryName().compareTo(o1.getCategoryName());
				}
			});
			sortDescending = true;
		}
		return null;
	}


	public static String sortByPrice(List<ItemsDTO> DTOList){
		if(sortDescending){
			Collections.sort(DTOList, new Comparator<ItemsDTO>() {
				@Override
				public int compare(ItemsDTO o1, ItemsDTO o2) {
					return Float.compare(o1.getPrice(), o2.getPrice());
				}
			});
			sortDescending = false;
		}else{
			Collections.sort(DTOList, new Comparator<ItemsDTO>() {
				@Override
				public int compare(ItemsDTO o1, ItemsDTO o2) {
					return Float.compare(o2.getPrice(), o1.getPrice());
				}
			});
			sortDescending = true;
		}
		return null;
	}

}