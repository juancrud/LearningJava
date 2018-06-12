package com.juancrud.framework.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import com.juancrud.framework.entity.interfaces.IGenericEntity;

@SuppressWarnings("serial")
@MappedSuperclass
public abstract class GenericEntity implements IGenericEntity, Serializable {
	
	//Fields
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="Id", nullable = false)
	private long id;
	
	//Constructors
	public GenericEntity() {
	}
	
	public GenericEntity(long id) {
		this.id = id;
	}
	
	//Getters - Setters
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
}
