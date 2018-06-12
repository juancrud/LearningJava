package com.juancrud.mywebapp.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class OrderModel {
	
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
	
	//Fields
	private long id;
	
	private Date date;
	
	private long marketId;
	
	private String marketName;
	
	private Collection<OrderLineModel> orderLines;
	
	//Constructors
	public OrderModel() {
		this.orderLines = new ArrayList<OrderLineModel>();
		this.date = new Date();
	}
	
	public OrderModel(long id, Date date, long marketId, String marketName) {
		this();
		this.id = id;
		this.date = date;
		this.marketId = marketId;
		this.marketName = marketName;
	}
	
	//Getters - Setters
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public long getMarketId() {
		return marketId;
	}

	public void setMarketId(long marketId) {
		this.marketId = marketId;
	}
	
	public String getMarketName() {
		return marketName;
	}

	public void setMarketName(String marketName) {
		this.marketName = marketName;
	}

	public Collection<OrderLineModel> getOrderLines() {
		return orderLines;
	}

	public void setOrderLines(Collection<OrderLineModel> orderLines) {
		this.orderLines = orderLines;
	}
	
	//Methods
	public String getDateString() {
		return dateFormat.format(date);
	}
	
	public double getTotalPrice() {
		double totalPrice = 0;
		for(OrderLineModel orderLineModel : orderLines) {
			totalPrice += orderLineModel.getLinePrice();
		}
		return totalPrice;
	}
}
