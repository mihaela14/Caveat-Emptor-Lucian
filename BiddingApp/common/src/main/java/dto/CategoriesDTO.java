package dto;

import java.io.Serializable;
import java.util.List;

public class CategoriesDTO implements Serializable{

	private static final long serialVersionUID = -4526595782465607986L;


	private int id;

	private String text;

	private String description;

	private int parentId;
	
	private List<CategoriesDTO>nodes;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return text;
	}
	public void setName(String name) {
		this.text = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	public List<CategoriesDTO> getNodes() {
		return nodes;
	}
	public void setNodes(List<CategoriesDTO> nodes) {
		this.nodes = nodes;
	}
}
