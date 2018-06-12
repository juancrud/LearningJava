package com.juancrud.utilities.encrypter;

public class Test {

	public static void main(String[] args) {
		final String password = "juancrud1251";
		final String wrongPassword = "password";
		
		//TEST: MD5
		AbstractEncrypter md5Encrypter = new Md5Encrypter();
		String md5Password = md5Encrypter.encrypt(password);
		System.out.println("MD5 password is: " + md5Password);
		
		boolean validMD5_1 = md5Encrypter.validate(password, md5Password);
		boolean validMD5_2 = md5Encrypter.validate(wrongPassword, md5Password);
		
		System.out.println("Valid password is valid? " + validMD5_1);
		System.out.println("Valid password is valid? " + validMD5_2);
		
		//TEST: Salted MD5
		AbstractEncrypter saltedMd5Encrypter = new SaltedMd5Encrypter();
		String saltedMd5Password = saltedMd5Encrypter.encrypt(password);
		System.out.println("Salted MD5 password is: " + saltedMd5Password);
		
		boolean validSaltedMD5_1 = saltedMd5Encrypter.validate(password, saltedMd5Password);
		boolean validSaltedMD5_2 = saltedMd5Encrypter.validate(wrongPassword, saltedMd5Password);
		
		System.out.println("Valid password is valid? " + validSaltedMD5_1);
		System.out.println("Valid password is valid? " + validSaltedMD5_2);
		
		//TEST: SHA-1
		AbstractEncrypter sha1Encrypter = new Sha1Encrypter();
		String sha1Password = sha1Encrypter.encrypt(password);
		System.out.println("SHA-1 password is: " + sha1Password);
		
		boolean validSHA1_1 = sha1Encrypter.validate(password, sha1Password);
		boolean validSHA1_2 = sha1Encrypter.validate(wrongPassword, sha1Password);
		
		System.out.println("Valid password is valid? " + validSHA1_1);
		System.out.println("Valid password is valid? " + validSHA1_2);
		
		//TEST: SHA-256
		AbstractEncrypter sha256Encrypter = new Sha1Encrypter();
		String sha256Password = sha256Encrypter.encrypt(password);
		System.out.println("SHA-256 password is: " + sha256Password);
		
		boolean validSHA256_1 = sha256Encrypter.validate(password, sha256Password);
		boolean validSHA256_2 = sha256Encrypter.validate(wrongPassword, sha256Password);
		
		System.out.println("Valid password is valid? " + validSHA256_1);
		System.out.println("Valid password is valid? " + validSHA256_2);
		
		//TEST: SHA-384
		AbstractEncrypter sha384Encrypter = new Sha1Encrypter();
		String sha384Password = sha384Encrypter.encrypt(password);
		System.out.println("SHA-384 password is: " + sha384Password);
		
		boolean validSHA384_1 = sha384Encrypter.validate(password, sha384Password);
		boolean validSHA384_2 = sha384Encrypter.validate(wrongPassword, sha384Password);
		
		System.out.println("Valid password is valid? " + validSHA384_1);
		System.out.println("Valid password is valid? " + validSHA384_2);
		
		//TEST: SHA-512
		AbstractEncrypter sha512Encrypter = new Sha512Encrypter();
		String sha512Password = sha512Encrypter.encrypt(password);
		System.out.println("SHA-512 password is: " + sha512Password);
		
		boolean validSHA512_1 = sha512Encrypter.validate(password, sha512Password);
		boolean validSHA512_2 = sha512Encrypter.validate(wrongPassword, sha512Password);
		
		System.out.println("Valid password is valid? " + validSHA512_1);
		System.out.println("Valid password is valid? " + validSHA512_2);
		
		//TEST: PBKDF2WithHmacSHA1
		Pbkdf2WithHmacSha1Encrypter diffEncrypter = new Pbkdf2WithHmacSha1Encrypter();
		String difficultPassword = diffEncrypter.encrypt(password);
		System.out.println("PBKDF2WithHmacSHA1 password is: " + difficultPassword);
		
		boolean validDifficult_1 = diffEncrypter.validate(password, difficultPassword);
		boolean validDifficult_2 = diffEncrypter.validate(wrongPassword, difficultPassword);
		
		System.out.println("Valid password is valid? " + validDifficult_1);
		System.out.println("Valid password is valid? " + validDifficult_2);
		
	}

}
