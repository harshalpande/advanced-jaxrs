package org.harshal.javabrains.jwt.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.harshal.javabrains.jwt.model.JwtTokenNeeded;

@Path("echo-endpoints")
@Produces(MediaType.TEXT_PLAIN)
@Consumes(MediaType.TEXT_PLAIN)
public class SomeEndpoints {
	
	@GET
	public Response echo(@QueryParam("message") String message) {
		return Response.ok().entity(message == null ? "No Message" : message).build();
	}
	
	@GET
	@Path("jwt")
	@JwtTokenNeeded		// New Annotation Created see interface with namebinding
	public Response echoWithJWT(@QueryParam("message") String message) {
		return Response.ok().entity(message == null ? "No Message" : "JWT Authorized " + message).build();
	}
	

}
