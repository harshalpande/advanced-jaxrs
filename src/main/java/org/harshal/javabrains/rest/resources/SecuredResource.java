package org.harshal.javabrains.rest.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("secured")
public class SecuredResource {

	@GET
	@Path("basicAuth")
	@Produces(MediaType.TEXT_PLAIN)
	public String getSecuredString() {
		return "This API is secured and accessible after BASIC-AUTH";
	}
}
