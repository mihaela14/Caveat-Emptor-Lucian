package com.biddingapp.items.utilities;

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
}