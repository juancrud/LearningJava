package com.juancrud.mywebapp.model;

import org.hibernate.validator.constraints.NotBlank;

public class MeasureModel {
	
	//Fields
	private long id;
	
	@NotBlank
	private String name;
	
	private String alias;
	
	//Constructors
	public MeasureModel() {
	}
	
	public MeasureModel(long id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public MeasureModel(long id, String name, String alias) {
		this(id, name);
		this.alias = alias;
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

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}
}
