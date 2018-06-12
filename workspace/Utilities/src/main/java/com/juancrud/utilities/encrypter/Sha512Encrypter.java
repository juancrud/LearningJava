package com.juancrud.utilities.encrypter;

public class Sha512Encrypter extends AbstractEncrypter {
	
	Sha512Encrypter() {
		super("SHA-512");
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
