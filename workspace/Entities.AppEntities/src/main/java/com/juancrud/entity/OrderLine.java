package com.juancrud.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.juancrud.framework.entity.AuditableGenericEntity;

@SuppressWarnings("serial")
@Entity
@Table(name="OrderLine")
public class OrderLine extends AuditableGenericEntity {
	
	//Fields
	@Column(name="Quantity", nullable = false)
	private double quantity;
	
	@Column(name="UnitPrice", nullable = false)
	private double unitPrice;
	
	@OneToOne
	@JoinColumn (name="ProductId")
	private Product product;
	
	@ManyToOne
	@JoinColumn (name="OrderId")
	private Order order;
	
	//Constructors
	public OrderLine() {
	}
	
	public OrderLine(Product product, int quantity, double unitPrice) {
		this.product = product;
		this.quantity = quantity;
		this.unitPrice = unitPrice;
	}
	
	//Getters - Setters
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

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
		if(!order.getOrderLines().contains(this)) {
			order.getOrderLines().add(this);
		}
	}
}
