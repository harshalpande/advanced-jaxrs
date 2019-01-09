package org.harshal.javabrains.jwt.resource;

import java.security.Key;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.harshal.javabrains.jwt.hibernate.CRUDOperations;
import org.harshal.javabrains.jwt.model.KeyModel;
import org.harshal.javabrains.jwt.operations.KeyFunctions;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Path("users")
public class UserEndPoint {
	
	CRUDOperations crudOperations = new CRUDOperations();

	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response authenticateUser(@FormParam("userId") String userId, 
			@FormParam("password") String password) {
		
		try {
			if (!authenticate(userId, password)) {
				throw new Exception();
			}
			
			String token = issueToken(userId, password);

			return Response.ok().header(HttpHeaders.AUTHORIZATION, "Bearer " + token).build();

		} catch (Exception e) {
			return Response.status(Status.FORBIDDEN).build();
		}

	}

	private String issueToken(String userId, String password) {
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MINUTE, 15);
		
		KeyFunctions keyInstance = KeyFunctions.getInstance();
		Key key = keyInstance.getKey();
		
		String jwtToken = Jwts.builder()
							.setSubject(userId)
							.setIssuer("Harshal Pande")				// This should be URI.absolutePath
							.setIssuedAt(date)
							.setExpiration(cal.getTime())
							.signWith(SignatureAlgorithm.HS512, key)
							.compact();
		
		KeyModel model = new KeyModel();
		model.setUser(userId);
		model.setPassword(password);
		model.setKey(keyInstance.getKeyInString(key));
		model.setTtl(Timestamp.valueOf(LocalDateTime.now().plusMinutes(15)));
		model.setToken(jwtToken);
		crudOperations.updateOperation(model);
		
		return jwtToken;
		
	}

	/**
	 * Hibernate Call to database to Authenticate the User with credentials
	 * @param userId
	 * @param password
	 * @return
	 */
	private boolean authenticate(String userId, String password) {
		
		KeyModel model = crudOperations.fetchOperation(userId);
		if (model != null) {
			if (model.getPassword().equals(password)) return true;
		}
		return false;
	}
}
