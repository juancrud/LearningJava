package com.juancrud.mywebapp.model;

import org.hibernate.validator.constraints.NotBlank;

public class MarketModel {
	
	//Fields
	private long id;
	
	@NotBlank
	private String name;
	
	//Constructors
	public MarketModel() {
	}
	
	public MarketModel(long id, String name) {
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
	
}
