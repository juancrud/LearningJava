package com.juancrud.mywebapp.model;

import org.hibernate.validator.constraints.NotBlank;

public class ProductModel {
	
	//Fields
	private long id;
	
	@NotBlank
	private String name;
	
	private long measureId;
	
	private long parentId;
	
	//Constructors
	public ProductModel() {
	}
	
	public ProductModel(long parentId) {
		this.parentId = parentId;
	}
	
	public ProductModel(long id, String name, long measureId, long parentId) {
		this.id = id;
		this.name = name;
		this.measureId = measureId;
		this.parentId = parentId;
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

	public long getMeasureId() {
		return measureId;
	}

	public void setMeasureId(long measureId) {
		this.measureId = measureId;
	}

	public long getParentId() {
		return parentId;
	}

	public void setParentId(long parentId) {
		this.parentId = parentId;
	}

}
