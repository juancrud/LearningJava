package com.juancrud.mywebapp.model;

import java.util.ArrayList;
import java.util.Collection;

public class CatalogItemModel {
	
	//Fields
	private long id;
	private String name;
	private boolean isCategory;
	private Collection<CatalogItemModel> childCategories;
	private Collection<CatalogItemModel> childProducts;
	
	//Constructors
	public CatalogItemModel() {
		this.childCategories = new ArrayList<CatalogItemModel>();
		this.childProducts = new ArrayList<CatalogItemModel>();
	}
	
	public CatalogItemModel(long id, String name, boolean isCategory) {
		this();
		this.id = id;
		this.name = name;
		this.isCategory = isCategory;
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

	public boolean isCategory() {
		return isCategory;
	}

	public void setCategory(boolean isCategory) {
		this.isCategory = isCategory;
	}

	public Collection<CatalogItemModel> getChildCategories() {
		return childCategories;
	}

	public void setChildCategories(Collection<CatalogItemModel> childCategories) {
		this.childCategories = childCategories;
	}

	public Collection<CatalogItemModel> getChildProducts() {
		return childProducts;
	}

	public void setChildProducts(Collection<CatalogItemModel> childProducts) {
		this.childProducts = childProducts;
	}
	
}
