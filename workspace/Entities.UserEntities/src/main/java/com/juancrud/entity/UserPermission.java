package com.juancrud.entity;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.juancrud.framework.entity.AuditableGenericEntity;

@SuppressWarnings("serial")
@Entity
@Table(name = "UserPermission")
public class UserPermission extends AuditableGenericEntity {

	// Fields
	@Column(name = "Name", nullable = false)
	private String name;

	@ManyToMany(cascade = CascadeType.ALL, mappedBy = "userPermissions", fetch = FetchType.EAGER)
	private Collection<User> users;

	// Constructors
	public UserPermission() {
		users = new ArrayList<User>();
	}

	// Getters - Setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Collection<User> getUsers() {
		return users;
	}

	public void setUsers(Collection<User> users) {
		this.users = users;
	}

}
