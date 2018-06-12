package com.juancrud.entity.enums;

public enum UserStatus {
	active, //Active user
	deleted, //Deleted user
	disabled, //Temporarily disabled
	blocked, //Too many login errors
	reset, //Password reseted, have to change password
	incomplete //Just created, have to change password
}
