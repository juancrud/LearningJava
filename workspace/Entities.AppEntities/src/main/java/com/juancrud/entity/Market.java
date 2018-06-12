package com.juancrud.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.juancrud.framework.entity.AuditableGenericEntity;

@SuppressWarnings("serial")
@Entity
@Table(name = "Market")
public class Market extends AuditableGenericEntity {
	
	//Fields
	@Column(name="Name", nullable = false, unique = true)
	private String name;
	
	//Constructors
	public Market() {
	}
	
	public Market(String name) {
		this.setName(name);
	}

	//Getters - Setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
