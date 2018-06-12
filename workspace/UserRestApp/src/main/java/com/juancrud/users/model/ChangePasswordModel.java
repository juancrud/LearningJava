package com.juancrud.users.model;

public class ChangePasswordModel {
	
	//Fields
	private long id;
	private String password;
	
	//Constructors
	
	//Getters - Setters
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
