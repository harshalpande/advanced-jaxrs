package org.harshal.javabrains.jwt.filter;

import java.io.IOException;
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
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
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
			
			KeyModel model = crudOperations.fetchOperation("token", token);
			
			if (model != null && isValid(model)) {
				String key = model.getKey();
				Jws<Claims> jwsClaims = Jwts.parser().setSigningKey(key).parseClaimsJws(token);
				
				/**
				 * After getting claims successfully, just for second verification, validating
				 * the 'sub' (subject) from the received jwsClaims-body with the one that was
				 * assigned
				 */
				
				if (jwsClaims.getBody().get("sub").equals(model.getUser())) {
					System.out.println("#### Valid Token " + token);
				} else {
					throw new Exception();
				}
			} else {
				throw new Exception();
			}
		} catch (Exception e) {
			System.out.println("#### Invalid Token " + token);
			requestContext.abortWith(Response.status(Response.Status.FORBIDDEN).entity("Invalid Request").build());
		}
	}

	private boolean isValid(KeyModel model) {
		
		Timestamp timestamp = model.getTtl();
		if (Timestamp.valueOf(LocalDateTime.now()).before(timestamp)) {
			return true;
		}
		crudOperations.deleteKeyOperation(model.getUser());
		return false ;
	}

}
