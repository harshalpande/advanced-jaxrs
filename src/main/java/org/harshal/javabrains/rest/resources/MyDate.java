package org.harshal.javabrains.rest.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.harshal.javabrains.rest.model.DateEx;

@Path("date/{dateString}")
public class MyDate {
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getDate(@PathParam("dateString") DateEx dateString) {
		return "Got the Date " + dateString.toString();
	}

}
