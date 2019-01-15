package org.harshal.javabrains.jwt.model;

import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.KeyGenerator;

public class TestClass {

	/*public static void main(String[] args) {
		CRUDOperations crudOps = new CRUDOperations();
		KeyModel keyModel = new KeyModel();
		keyModel.setUser("harshal");
		keyModel.setPassword("harshal123");
		keyModel.setKey(generateKey());
		keyModel.setToken("jfiewoajimfjaiohgwpoi,fadipohfpoiahmtvaipoew,hfxoiahiohgioewhfiods");
		keyModel.setTtl(Timestamp.valueOf(LocalDateTime.now()));
		crudOps.createOperation(keyModel );
	}*/
	
	public static String generateKey() {
		String key = null;
		try {
			key = Base64.getEncoder().encode(KeyGenerator.getInstance("aes").generateKey().getEncoded()).toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return key;
	}

}
