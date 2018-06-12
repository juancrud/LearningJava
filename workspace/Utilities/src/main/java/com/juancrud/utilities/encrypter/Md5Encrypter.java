package com.juancrud.utilities.encrypter;

public class Md5Encrypter extends AbstractEncrypter {

	Md5Encrypter() {
		super("MD5");
	}

	@Override
	public String encrypt(String value) {
		return EncryptHelper.cryptValue(value, algorithmKey);
	}

	@Override
	public boolean validate(String originalValue, String storedValue) {
		return EncryptHelper.cryptValue(originalValue, algorithmKey).equals(storedValue);
	}
	
}
