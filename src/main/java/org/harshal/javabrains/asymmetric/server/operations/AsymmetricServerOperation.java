package org.harshal.javabrains.asymmetric.server.operations;

import java.io.File;
import java.nio.file.Files;
import java.security.Key;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.spec.PKCS8EncodedKeySpec;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;

import org.apache.commons.codec.binary.Base64;

public class AsymmetricServerOperation {

	private Cipher cipher;

	public static String ENCODING_TYPE = "UTF-8";
	public static final String ALGORITHM_TYPE = "RSA";
	public static final String PRIVATE_KEY_PATH = "KeyPair/privateKey";

	public AsymmetricServerOperation() throws NoSuchAlgorithmException, NoSuchPaddingException {
		this.cipher = Cipher.getInstance("RSA");
	}

	private Key getPrivateKey() throws Exception {
		File file = new File(PRIVATE_KEY_PATH);
		byte[] privateKeyInBytes = Files.readAllBytes(file.toPath());
		PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(privateKeyInBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM_TYPE);
		return keyFactory.generatePrivate(spec);
	}

	public String getEncryptedText(String textToEncrypt) throws Exception {
		this.cipher.init(Cipher.ENCRYPT_MODE, getPrivateKey());
		return Base64.encodeBase64String(cipher.doFinal(textToEncrypt.getBytes(ENCODING_TYPE)));
	}

	public String getDecryptedText(String textToDecrypt) throws Exception {
		this.cipher.init(Cipher.DECRYPT_MODE, getPrivateKey());
		return new String(cipher.doFinal(Base64.decodeBase64(textToDecrypt.getBytes())), ENCODING_TYPE);
	}

}
