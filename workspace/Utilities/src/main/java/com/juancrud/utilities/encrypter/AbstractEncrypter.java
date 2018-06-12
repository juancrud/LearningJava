package com.juancrud.utilities.encrypter;

public abstract class AbstractEncrypter implements IEncrypter {
	
	static final String SEPARATOR = "/";
	
	protected final String algorithmKey;
	
	AbstractEncrypter(String algorithmKey) {
        this.algorithmKey = algorithmKey;
    }
	
}
