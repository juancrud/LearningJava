package com.juancrud.utilities.encrypter;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class Pbkdf2WithHmacSha1Encrypter extends AbstractEncrypter {
	
	private static final int ITERATIONS = 1000;
	private static final int HASH_LENGTH = 64;
	
	public Pbkdf2WithHmacSha1Encrypter() {
		super("PBKDF2WithHmacSHA1");
	}

	@Override
	public String encrypt(String value) {
		int keyLength = HASH_LENGTH*8;
		
		String salt = EncryptHelper.getSalt();
		byte[] saltBytes = salt.getBytes();
        char[] chars = value.toCharArray();
        
        try{
        	PBEKeySpec spec = new PBEKeySpec(chars, saltBytes, ITERATIONS, keyLength);
            SecretKeyFactory skf = SecretKeyFactory.getInstance(algorithmKey);
            byte[] hash = skf.generateSecret(spec).getEncoded();
            return ITERATIONS + SEPARATOR + EncryptHelper.toHex(saltBytes) + SEPARATOR + EncryptHelper.toHex(hash);
        }
        catch(NoSuchAlgorithmException | InvalidKeySpecException ex){
        	return null;
        }
	}

	@Override
	public boolean validate(String originalValue, String storedValue) {
		String[] parts = storedValue.split(SEPARATOR);
        int iterations = Integer.parseInt(parts[0]);
        byte[] salt = EncryptHelper.fromHex(parts[1]);
        byte[] hash = EncryptHelper.fromHex(parts[2]);
        
        try{
        	PBEKeySpec spec = new PBEKeySpec(originalValue.toCharArray(), salt, iterations, hash.length * 8);
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            byte[] testHash = skf.generateSecret(spec).getEncoded();
            
            int diff = hash.length ^ testHash.length;
            for(int i = 0; i < hash.length && i < testHash.length; i++)
            {
                diff |= hash[i] ^ testHash[i];
            }
            return diff == 0;
        }
        catch(NoSuchAlgorithmException | InvalidKeySpecException ex){
        	return false;
        }
	}

}
