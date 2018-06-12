package com.juancrud.utilities.encrypter;

public class Sha384Encrypter extends AbstractEncrypter {
	
	Sha384Encrypter() {
		super("SHA-384");
	}

	@Override
	public String encrypt(String value) {
		String salt = EncryptHelper.getSalt();
		return EncryptHelper.cryptValue(value, salt, algorithmKey);
	}

	@Override
	public boolean validate(String originalValue, String storedValue) {
		String[] parts = storedValue.split(SEPARATOR);
		String salt = parts[0];
		return EncryptHelper.cryptValue(originalValue, salt, algorithmKey).equals(storedValue);
	}
	
}
