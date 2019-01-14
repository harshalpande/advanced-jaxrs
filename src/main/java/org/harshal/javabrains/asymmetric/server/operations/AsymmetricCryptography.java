package org.harshal.javabrains.asymmetric.server.operations;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.apache.commons.codec.binary.Base64;

public class AsymmetricCryptography {

	public static final String ALGORITHM_TYPE = "RSA";
	public static final String PRIVATE_KEY_PATH = "KeyPair/privateKey";
	public static final String PUBLIC_KEY_PATH = "KeyPair/publicKey";

	private Cipher cipher;
	
	public static String ENCODING_TYPE = "UTF-8";

	public AsymmetricCryptography() throws NoSuchAlgorithmException, NoSuchPaddingException {
		this.cipher = Cipher.getInstance("RSA");
	}

	public PrivateKey getPrivateKey(String fileName) throws Exception {
		byte[] privateKeyInBytes = Files.readAllBytes(new File(fileName).toPath());
		PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(privateKeyInBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM_TYPE);
		return keyFactory.generatePrivate(spec);
	}

	public PublicKey getPublicKey(String fileName) throws Exception {
		byte[] publicKeyInbytes = Files.readAllBytes(new File(fileName).toPath());
		X509EncodedKeySpec spec = new X509EncodedKeySpec(publicKeyInbytes);
		KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM_TYPE);
		return keyFactory.generatePublic(spec);
	}

	public String getEncryptedText(PrivateKey key, String textToEncrypt)
			throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
		this.cipher.init(Cipher.ENCRYPT_MODE, key);
		return Base64.encodeBase64String(cipher.doFinal(textToEncrypt.getBytes(ENCODING_TYPE)));
	}

	public String getDecryptedText(PublicKey key, String textToDecrypt)
			throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
		this.cipher.init(Cipher.DECRYPT_MODE, key);
		return new String(cipher.doFinal(Base64.decodeBase64(textToDecrypt.getBytes())), ENCODING_TYPE);
	}

	/*public static void main(String[] args)
			throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException, Exception {
		AsymmetricCryptography asymmetricCryptography = new AsymmetricCryptography();

		String encryptedText = asymmetricCryptography.getEncryptedText(
				asymmetricCryptography.getPrivateKey("KeyPair/privateKey"), "Harshal Shankarlal Pande");

		System.out.println(encryptedText);

		String decryptedText = asymmetricCryptography
				.getDecryptedText(asymmetricCryptography.getPublicKey("KeyPair/publicKey"), encryptedText);

		System.out.println(decryptedText);
	}*/
}
