package com.juancrud.framework.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import com.juancrud.framework.entity.interfaces.IAuditable;

@SuppressWarnings("serial")
@MappedSuperclass
public abstract class AuditableGenericEntity extends GenericEntity implements IAuditable {

	// Fields
	@Column(name = "DateCreated", nullable = false, updatable = false)
	private Date dateCreated;

	@Column(name = "DateUpdated", nullable = true, insertable = false)
	private Date dateUpdated;
	
	// Constructors
	public AuditableGenericEntity() {
	}
	
	public AuditableGenericEntity(long id) {
		super(id);
	}

	// Getters - Setters
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Date getDateCreated() {
		return this.dateCreated;
	}

	public void setDateUpdated(Date dateUpdated) {
		this.dateUpdated = dateUpdated;
	}

	public Date getDateUpdated() {
		return this.dateUpdated;
	}
	
	// Methods
	@PrePersist
	protected void onCreate() {
		dateCreated = new Date();
	}

	@PreUpdate
	protected void onUpdate() {
		dateUpdated = new Date();
	}
}
