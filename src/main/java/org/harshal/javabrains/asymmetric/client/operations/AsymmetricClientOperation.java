package org.harshal.javabrains.asymmetric.client.operations;

import java.io.File;
import java.nio.file.Files;
import java.security.Key;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;

import org.apache.commons.codec.binary.Base64;

public class AsymmetricClientOperation {

	private Cipher cipher;
	public static String ENCODING_TYPE = "UTF-8";
	public static final String ALGORITHM_TYPE = "RSA";
	public static final String PUBLIC_KEY_PATH = "KeyPair/publicKey";

	public AsymmetricClientOperation() throws NoSuchAlgorithmException, NoSuchPaddingException {
		this.cipher = Cipher.getInstance("RSA");
	}

	private Key getPublicKey() throws Exception {
		byte[] publicKeyInbytes = Files.readAllBytes(new File(PUBLIC_KEY_PATH).toPath());
		X509EncodedKeySpec spec = new X509EncodedKeySpec(publicKeyInbytes);
		KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM_TYPE);
		return keyFactory.generatePublic(spec);
	}

	public String getDecryptedText(String textToDecrypt)
			throws Exception {
		this.cipher.init(Cipher.DECRYPT_MODE, getPublicKey());
		return new String(cipher.doFinal(Base64.decodeBase64(textToDecrypt.getBytes())), ENCODING_TYPE);
	}

	public String getEncryptedText(String textToEncrypt) throws Exception {
		this.cipher.init(Cipher.ENCRYPT_MODE, getPublicKey());
		return Base64.encodeBase64String(cipher.doFinal(textToEncrypt.getBytes(ENCODING_TYPE)));
	}
	
	/*
	 * public static void main(String[] args) throws InvalidKeyException,
	 * IllegalBlockSizeException, BadPaddingException, Exception {
	 * AsymmetricCryptography asymmetricCryptography = new AsymmetricCryptography();
	 * PublicKey publicKey =
	 * asymmetricCryptography.getPublicKey("KeyPair/publicKey"); PrivateKey
	 * privateKey = asymmetricCryptography.getPrivateKey("KeyPair/privateKey");
	 * 
	 * String encryptedText = asymmetricCryptography.getEncryptedText( publicKey,
	 * "Harshal Shankarlal Pande");
	 * 
	 * System.out.println(encryptedText);
	 * 
	 * String decryptedText = asymmetricCryptography .getDecryptedText(privateKey,
	 * encryptedText);
	 * 
	 * System.out.println(decryptedText); }
	 */
}
