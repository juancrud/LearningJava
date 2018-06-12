package com.juancrud.utilities.encrypter;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class EncryptHelper {

	public static String cryptValue(String value, String algorithmKey) {
		try {
			MessageDigest md = MessageDigest.getInstance(algorithmKey);
			byte[] bytes = md.digest(value.getBytes());

			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < bytes.length; i++) {
				sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			}
			return sb.toString();
		} catch (NoSuchAlgorithmException ex) {
			return null;
		}
	}

	public static String cryptValue(String value, String salt, String algorithmKey) {
		try {
			MessageDigest md = MessageDigest.getInstance(algorithmKey);
			md.update(salt.getBytes());
			byte[] bytes = md.digest(value.getBytes());
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < bytes.length; i++) {
				sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			}
			return salt + AbstractEncrypter.SEPARATOR + sb.toString();
		} catch (NoSuchAlgorithmException ex) {
			return null;
		}
	}

	public static String toHex(byte[] array) {
		BigInteger bi = new BigInteger(1, array);
		String hex = bi.toString(16);
		int paddingLength = (array.length * 2) - hex.length();
		if (paddingLength > 0) {
			return String.format("%0" + paddingLength + "d", 0) + hex;
		} else {
			return hex;
		}
	}

	public static byte[] fromHex(String hex) {
		byte[] bytes = new byte[hex.length() / 2];
		for (int i = 0; i < bytes.length; i++) {
			bytes[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
		}
		return bytes;
	}

	public static String getSalt() {
		try {
			SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
			byte[] salt = new byte[16];
			sr.nextBytes(salt);
			return salt.toString();
		} catch (NoSuchAlgorithmException ex) {
			return null;
		}
	}
}
