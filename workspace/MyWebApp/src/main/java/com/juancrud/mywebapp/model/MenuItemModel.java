package com.juancrud.mywebapp.model;

public class MenuItemModel {
	
	private String itemId;
	private String itemText;
	private String itemContentUrl;
	
	public MenuItemModel(String itemId, String itemText, String itemContentUrl) {
		this.itemId = itemId;
		this.itemText = itemText;
		this.itemContentUrl = itemContentUrl;
	}
	
	public String getItemId() {
		return itemId;
	}
	
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getItemText() {
		return itemText;
	}

	public void setItemText(String itemText) {
		this.itemText = itemText;
	}

	public String getItemContentUrl() {
		return itemContentUrl;
	}

	public void setItemContentUrl(String itemContentUrl) {
		this.itemContentUrl = itemContentUrl;
	}
	
}
