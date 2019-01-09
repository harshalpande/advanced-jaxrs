package org.harshal.javabrains.jwt.operations;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;

public class KeyFunctions {
	
	private static final String ALGORITHM = "aes";
	
	private KeyFunctions () {
		// private Constructor
	}
	
	private static KeyFunctions INSTANCE = getInstance();

	public static KeyFunctions getInstance() {
		if (INSTANCE == null) {
			synchronized (KeyFunctions.class) {
				if (INSTANCE == null) {
					return new KeyFunctions();
				}
			}
		}
		return INSTANCE;
	}
	
	public Key getKey() {
		try {
			return KeyGenerator.getInstance(ALGORITHM).generateKey();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * <b>SecretKey to String:</b>
	 * 
	 * <u>create new key</u>
	 * <p>
	 * SecretKey secretKey = KeyGenerator.getInstance("AES").generateKey();
	 * </p>
	 * <u>get base64 encoded version of the key</u>
	 * <p>
	 * String encodedKey =
	 * Base64.getEncoder().encodeToString(secretKey.getEncoded());
	 * </p>
	 * 
	 * @return String
	 */
	public String getKeyInString(Key secretKey) {
		String encodedKey = null;
		encodedKey = Base64.getEncoder().encodeToString(secretKey.getEncoded());
		return encodedKey;
	}

	/**
	 * <b>String to SecretKey</b>
	 * 
	 * <u>decode the base64 encoded string</u>
	 * <p>
	 * byte[] decodedKey = Base64.getDecoder().decode(encodedKey);
	 * </p>
	 * <u>rebuild key using SecretKeySpec</u>
	 * <p>
	 * SecretKey originalKey = new SecretKeySpec(decodedKey, 0, decodedKey.length,
	 * "AES");
	 * </p>
	 * 
	 * @param encodedKey
	 * @return
	 */
	public Key getKeyfromString(String encodedKey) {
		byte[] decodedKey = Base64.getDecoder().decode(encodedKey);
		Key originalKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, ALGORITHM);
		return originalKey;
	}
}
