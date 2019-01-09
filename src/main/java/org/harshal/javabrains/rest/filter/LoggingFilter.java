package org.harshal.javabrains.rest.filter;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;

@Provider
public class LoggingFilter implements ContainerRequestFilter, ContainerResponseFilter {

	/**
	 * Implemented Method of ContainerResponseFilter. the code written in it runs
	 * after the response is formed and before the response is sent to the User
	 */
	@Override
	public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext)
			throws IOException {
		System.out.println(responseContext.getStatusInfo().getStatusCode() + " : "
				+ responseContext.getStatusInfo().getReasonPhrase());
	}

	/**
	 * Implemented Method of ContainerRequestContext. The code written inside it
	 * runs after every request is made.
	 */
	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		System.out.println(requestContext.getUriInfo().getRequestUri());
	}

}
