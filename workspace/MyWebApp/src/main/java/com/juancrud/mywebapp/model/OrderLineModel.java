package com.juancrud.mywebapp.model;

public class OrderLineModel {
	
	//Fields
	private long id;
	
	private long productId;
	
	private String productName;
	
	private double quantity;
	
	private double unitPrice;
	
	private int index;
	
	//Constructors
	public OrderLineModel() {
		this.quantity = 1;
	}
	
	public OrderLineModel(int index) {
		this();
		this.index = index;
	}
	
	public OrderLineModel(long id, long productId, String productName, int quantity, double unitPrice, int index) {
		this.id = id;
		this.productId = productId;
		this.productName = productName;
		this.quantity = quantity;
		this.unitPrice = unitPrice;
		this.index = index;
	}
	
	//Getters - Setters
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}
	
	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public double getQuantity() {
		return quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}
	
	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
	
	//Methods
	public double getLinePrice() {
		return quantity * unitPrice;
	}
}
