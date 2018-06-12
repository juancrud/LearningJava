package com.juancrud.framework.service.enums;

public enum ResponseType {
	CREATE(1),
	UPDATE(2),
	DELETE(3),
	READ(4);
	
	private int typeCode;
	
	private ResponseType(int value) {
		typeCode = value;
	}
	
	public int getTypeCode() {
		return typeCode;
	}
}
