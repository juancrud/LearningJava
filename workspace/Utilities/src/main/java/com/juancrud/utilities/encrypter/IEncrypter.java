package com.juancrud.utilities.encrypter;

public interface IEncrypter {
	
	public String encrypt(String value);
	public boolean validate(String originalValue, String storedValue);
	
}
