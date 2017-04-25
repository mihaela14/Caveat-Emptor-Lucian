package com.biddingapp.categories;

import java.util.List;

public class Tree {

	String name;
	List<Tree> treeList;
	
	public Tree() {
		super();
	}
	
	public String getName() {
		return name;
	}	
	public void setName(String name) {
		this.name = name;
	}
	public List<Tree> getTreeList() {
		return treeList;
	}
	public void setTreeList(List<Tree> treeList) {
		this.treeList = treeList;
	}
	
}
