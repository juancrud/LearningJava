package com.juancrud.mywebapp.model;

import org.hibernate.validator.constraints.NotBlank;

public class CategoryModel {
	
	//Fields
	private long id;
	
	@NotBlank
	private String name;
	
	private long parentId;
	
	//Constructors
	public CategoryModel() {
	}
	
	public CategoryModel(long parentId) {
		this.parentId = parentId;
	}
	
	public CategoryModel(long id, String name, long parentId) {
		this(parentId);
		this.id = id;
		this.name = name;
	}
	
	//Getters - Setters
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getParentId() {
		return parentId;
	}

	public void setParentId(long parentId) {
		this.parentId = parentId;
	}
}
