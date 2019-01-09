package org.harshal.javabrains.jwt.filter;

import java.io.IOException;
import java.security.Key;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import org.harshal.javabrains.jwt.hibernate.CRUDOperations;
import org.harshal.javabrains.jwt.model.JwtTokenNeeded;
import org.harshal.javabrains.jwt.model.KeyModel;
import org.harshal.javabrains.jwt.operations.KeyFunctions;

import io.jsonwebtoken.Jwts;

@Provider
@JwtTokenNeeded
@Priority(Priorities.AUTHENTICATION)
public class JwtTokenNeededFilter implements ContainerRequestFilter{
	
	CRUDOperations crudOperations = new CRUDOperations();
	
	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
		String token = null;
		try {
			token = authorizationHeader.replace("Bearer", "").trim();
			
			KeyModel model = crudOperations.fetchOperation(token);
			
			if (model != null && isValid(model)) {
				Key key = KeyFunctions.getInstance().getKeyfromString(model.getKey());
				Jwts.parser().setSigningKey(key).parseClaimsJwt(token);
				System.out.println("#### Valid Token " + token);
			} else {
				throw new Exception();
			}
		} catch (Exception e) {
			System.out.println("#### Invalid Token " + token);
			requestContext.abortWith(Response.status(Response.Status.FORBIDDEN).build());
		}
	}

	private boolean isValid(KeyModel model) {
		
		Timestamp timestamp = model.getTtl();
		if (Timestamp.valueOf(LocalDateTime.now()).after(timestamp)) {
			return true;
		}
		crudOperations.deleteKeyOperation(model.getUser());
		return false ;
	}

}
