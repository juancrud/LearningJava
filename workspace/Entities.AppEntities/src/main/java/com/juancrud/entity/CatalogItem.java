package com.juancrud.entity;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import com.juancrud.framework.entity.AuditableGenericEntity;

@SuppressWarnings("serial")
@MappedSuperclass
public abstract class CatalogItem extends AuditableGenericEntity {
	
	//Fields
	@Column(name="Name", nullable = false)
	protected String name;
	
	@ManyToOne
	@JoinColumn (name="ParentId")
	protected Category parent;
	
	//Constructors
	public CatalogItem() {
	}
	
	public CatalogItem(long id) {
		super(id);
	}
	
	public CatalogItem(String name, Category parent) {
		this();
		this.name = name;
		this.parent = parent;
	}
	
	//Getters - Setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Category getParent() {
		return parent;
	}
	
	public void setParent(Category parent) {
		this.parent = parent;
	}
	
}
