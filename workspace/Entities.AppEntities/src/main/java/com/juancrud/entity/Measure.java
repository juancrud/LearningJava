package com.juancrud.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.juancrud.framework.entity.AuditableGenericEntity;

@SuppressWarnings("serial")
@Entity
@Table(name="Measure")
public class Measure extends AuditableGenericEntity {
	
	//Fields
	@Column(name="Name", nullable = false, unique = true)
	private String name;
	
	@Column(name="Alias", nullable = true, unique = true)
	private String alias;
	
	//Constructors
	public Measure() {
	}
	
	public Measure(String name, String alias) {
		this.setName(name);
		this.setAlias(alias);
	}

	//Getters - Setters
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
