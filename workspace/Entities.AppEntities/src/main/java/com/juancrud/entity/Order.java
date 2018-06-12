package com.juancrud.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.juancrud.framework.entity.AuditableGenericEntity;

@SuppressWarnings("serial")
@Entity
@Table(name="Orders")
public class Order extends AuditableGenericEntity {
	
	//Fields
	@Column(name="Date", nullable=false)
	private Date date;
	
	@OneToOne
	@JoinColumn (name="MarketId", nullable=false)
	private Market market;
	
	@OneToMany (mappedBy="order", fetch=FetchType.EAGER, cascade=CascadeType.ALL, orphanRemoval=true)
	private Collection<OrderLine> orderLines;
	
	//Constructors
	public Order(){
		this.orderLines = new ArrayList<OrderLine>();
	}
	
	public Order(Date date, Market market) {
		this();
		this.setDate(date);
		this.setMarket(market);
	}

	//Getters - Setters
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Market getMarket() {
		return market;
	}

	public void setMarket(Market market) {
		this.market = market;
	}

	public Collection<OrderLine> getOrderLines() {
		return orderLines;
	}

	public void addOrderLine(OrderLine orderLine) {
		this.orderLines.add(orderLine);
		if(orderLine.getOrder() != this) {
			orderLine.setOrder(this);
		}
	}
	
}
