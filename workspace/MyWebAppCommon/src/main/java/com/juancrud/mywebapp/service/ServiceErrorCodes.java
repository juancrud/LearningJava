package com.juancrud.mywebapp.service;

public abstract class ServiceErrorCodes {
	
	public static final int GENERAL_ERROR = 1;
	
	//Market Errors
	public static final int GENERAL_MARKET_ERROR = 1000;
	public static final int CAN_NOT_DELETE_MARKET_IN_USE = 1001;
	
	//Measure Errors
	public static final int GENERAL_MEASURE_ERROR = 2000;
	public static final int CAN_NOT_DELETE_MEASURE_IN_USE = 2001;
	
	//Catalog Errors
	public static final int GENERAL_CATALOG_ERROR = 3000;
	public static final int CAN_NOT_DELETE_CATEGORY_WITH_PRODUCTS = 3001;
	public static final int CAN_NOT_DELETE_CATEGORY_WITH_NESTED_PRODUCTS = 3002;
	public static final int CAN_NOT_MOVE_CATEGORY_POTENTIAL_CIRCULAR_REFERENCE = 3003;
	public static final int CAN_NOT_SET_PARENT_TO_ITSELF_POTENTIAL_CIRCULAR_REFERENCE = 3004;
	public static final int CAN_NOT_DELETE_PRODUCT_IN_USE = 3005;
	
}
