package org.harshal.javabrains.jwt.model;

import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;

import javax.crypto.KeyGenerator;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class TestClass {

	public static void main(String[] args) {
		
		/*CRUDOperations dboperations = new CRUDOperations();
		
		KeyModel model = new KeyModel();
		
		model.setUser("harshal");
		model.setPassword("harshal123");
		Key key = KeyFunctions.getInstance().getKey();
		model.setKey(KeyFunctions.getInstance().getKeyInString(key));
		model.setToken("fjdsfjapnwei ufh7q324mucrfgc923yfg9vn8a3809CE qpHEPF8QYG4APFHEX7 3HEXFYEVR97HCEFNHG VOEALIFBC9WER3H29RCHAFEWMF83NUAY298CYF984F893HF98");
		model.setTtl(Timestamp.valueOf(LocalDateTime.now()));
		Session session = dboperations.getSession();
		session.beginTransaction();
		
		session.save(model);
		
		session.getTransaction().commit();
		session.close();
		
		Session session2 = dboperations.getSession(); 
		session2.beginTransaction();
		KeyModel model2 = (KeyModel) session2.get(KeyModel.class, "harshal");
		
		System.out.println(model2.getToken());
		System.out.println(model2.toString());*/
		
		
		
		String userId = "Harshal Pande";
		Date date = Calendar.getInstance().getTime();
		Calendar cal = Calendar.getInstance();
				cal.add(Calendar.MINUTE, 5);
		Date expiredDate = cal.getTime();
		
		String key = new TestClass().generateKey();
		
		
		// Marshall
		
		String jwtToken = Jwts.builder()
				.setSubject(userId )
				.setIssuer("Harshal Pande")				// This should be URI.absolutePath
				.setIssuedAt(date)
				.setExpiration(expiredDate)
				.signWith(SignatureAlgorithm.HS512, key)
				.compact();
		
		System.out.println(key);
		System.out.println(jwtToken);
		// unmarshall
		
		Jws<Claims> parseClaimsJws = Jwts.parser().setSigningKey(key).parseClaimsJws(jwtToken);
		
		
		System.out.println(parseClaimsJws.getHeader());
		System.out.println(parseClaimsJws.getBody());
		System.out.println(parseClaimsJws.toString());
		
		
	}
	
	public String generateKey() {
		String key = null;
		try {
			key = Base64.getEncoder().encode(KeyGenerator.getInstance("aes").generateKey().getEncoded()).toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return key;
	}

}
