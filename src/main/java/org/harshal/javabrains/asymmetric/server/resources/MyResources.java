package org.harshal.javabrains.asymmetric.server.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.harshal.javabrains.asymmetric.server.model.PublicPrivate;

@Path("asymmetric")
public class MyResources {
	
	@GET
	@PublicPrivate
	public Response parseURL() {
		return Response.status(Status.OK).build();
	}
	
	@GET
	@Path("/trial")
	public Response getAsymmetricParseURl() {
		return Response.status(Status.OK).entity("Routing Worked..!!").build();
	}

}
