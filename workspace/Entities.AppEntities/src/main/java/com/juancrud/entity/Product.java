package com.juancrud.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="Product")
public class Product extends CatalogItem {
	
	//Fields
	@OneToOne
	@JoinColumn (name="MeasureId", nullable=false)
	private Measure measure;
	
	//Constructors
	public Product() {
	}
	
	public Product(String name, Measure measure, Category category) {
		super(name, category);
		this.measure = measure;
	}
	
	//Getters - Setters
	@Override
	public void setParent(Category parent) {
		super.setParent(parent);
		if(!parent.getProducts().contains(this)) {
			parent.getProducts().add(this);
		}
	}
	
	public Measure getMeasure() {
		return measure;
	}

	public void setMeasure(Measure measure) {
		this.measure = measure;
	}
	
}
