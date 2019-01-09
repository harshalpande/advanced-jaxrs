package org.harshal.javabrains.rest.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("default")
public class HelloWorld {

	@GET
	public Response helloWorld() {
		return Response.status(Response.Status.OK).entity("Hello World!!!").build();
	}
}
