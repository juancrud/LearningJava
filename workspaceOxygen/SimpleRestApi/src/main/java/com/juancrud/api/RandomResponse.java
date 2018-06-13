package com.juancrud.api;

import java.util.Date;

public class RandomResponse {
	
	private int number;
	private Date date;
	
	public RandomResponse() {
	}
	
	public RandomResponse(int number) {
		setNumber(number);
		setDate(new Date());
	}
	
	public int getNumber() {
		return number;
	}
	
	public void setNumber(int number) {
		this.number = number;
	}
	
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
}
