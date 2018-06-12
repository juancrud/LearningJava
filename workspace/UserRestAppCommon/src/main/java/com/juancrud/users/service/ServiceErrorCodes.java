package com.juancrud.users.service;

public abstract class ServiceErrorCodes {
	
	public static final int GENERAL_ERROR = 1;
	
	//User Errors
	public static final int GENERAL_USER_ERROR = 1000;
	public static final int USER_NOT_FOUND = 1001;
	public static final int AUTHENTICATION_ERROR = 1002;
	public static final int USER_BLOCKED = 1003;
	public static final int USER_NEEDS_PASSWORD_CHANGE = 1004;
	
	//UserPermission Errors
	public static final int GENERAL_USER_PERMISSION_ERROR = 2000;
	public static final int USER_PERMISSION_NOT_FOUND = 1002;
	
}
