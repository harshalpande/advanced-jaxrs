package org.harshal.javabrains.rest.filter.secure;

import java.io.IOException;
import java.util.List;
import java.util.StringTokenizer;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.Provider;

import org.glassfish.jersey.internal.util.Base64;

@Provider
public class BasicAuthFilter implements ContainerRequestFilter {

	private final String AUTHORIZATION_KEY = "Authorization";
	private final String BASIC_STRING = "Basic ";
	private final String SECURED_URL_STRING = "secured";
	
	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		if (requestContext.getUriInfo().getPath().contains(SECURED_URL_STRING)) {		
			// implement only for APIs which contain SECURED in their URI
			
			List<String> listAuthHeader = requestContext.getHeaders().get(AUTHORIZATION_KEY);
			if (listAuthHeader != null && listAuthHeader.size() > 0) {
				String authValue = listAuthHeader.get(0); 		// get the first authorization header value
				authValue = authValue.replace(BASIC_STRING, "");
				
				authValue = Base64.decodeAsString(authValue);	
				// Decode from BASE64 to normal String using any BASE64 utility class api available.
				// However it is recommended to use org.glassfish.jersey.internal.util.Base64 Utility class for decoding.
				
				StringTokenizer tokenizer = new StringTokenizer(authValue, ":");
				String userName = tokenizer.nextToken();
				String password = tokenizer.nextToken();
	
				if ("harshal".equals(userName) && "Shankar_23".equals(password)) {
					return; 									
					// return statement means Filter executed successfully, 
					//so return only when it is really successful.
				}
			} 
				
			// for everything else there is ABORTWITH method of RequestContext 
			// which doesn't allow URLs to access resources.
			ResponseBuilder responseBuilder = Response.status(Status.UNAUTHORIZED)
					.entity("The Credentials didn't matched..!!!");
			requestContext.abortWith(responseBuilder.build());
			
		}
	}

}
