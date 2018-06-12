package com.juancrud.linq.tests.classes;

public class IntStringPair {
	
	private int intValue;
	private String stringValue;
	
	public IntStringPair(int intValue, String stringValue) {
		this.setIntValue(intValue);
		this.setStringValue(stringValue);
	}

	public int getIntValue() {
		return intValue;
	}

	public void setIntValue(int intValue) {
		this.intValue = intValue;
	}

	public String getStringValue() {
		return stringValue;
	}

	public void setStringValue(String stringValue) {
		this.stringValue = stringValue;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof IntStringPair)) {
			return false;
		}
		
		IntStringPair that = (IntStringPair)obj;
		return this.getIntValue() == that.getIntValue() && this.getStringValue() == that.getStringValue();
	}
	
}
