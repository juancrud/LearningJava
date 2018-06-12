package com.juancrud.entity;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="Category")
public class Category extends CatalogItem {
	
	@OneToMany (mappedBy="parent", fetch=FetchType.EAGER, cascade=CascadeType.REMOVE)
	private Collection<Category> subcategories;
	
	@OneToMany (mappedBy="parent", fetch=FetchType.EAGER, cascade=CascadeType.REMOVE)
	private Collection<Product> products;
	
	//Constructors
	public Category() {
		this.subcategories = new ArrayList<Category>();
		this.products = new ArrayList<Product>();
	}
	
	public Category(long id) {
		super(id);
		this.subcategories = new ArrayList<Category>();
		this.products = new ArrayList<Product>();
	}
	
	public Category(String name, Category parent) {
		super(name, parent);
		this.subcategories = new ArrayList<Category>();
		this.products = new ArrayList<Product>();
	}
	
	//Getters - Setters
	@Override
	public void setParent(Category parent) {
		super.setParent(parent);
		if(parent != null && !parent.getSubcategories().contains(this)) {
			parent.getSubcategories().add(this);
		}
	}
	
	public Collection<Category> getSubcategories() {
		return subcategories;
	}

	public void addSubcategory(Category subcategory) {
		this.subcategories.add(subcategory);
		if(subcategory.getParent() != this) {
			subcategory.setParent(this);
		}
	}

	public Collection<Product> getProducts() {
		return products;
	}

	public void addProduct(Product product) {
		this.products.add(product);
		if(product.getParent() != this) {
			product.setParent(this);
		}
	}
}
