package org.harshal.javabrains.rest.resources;

import java.util.Calendar;
import java.util.Date;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("messageBodyWriter")
public class MessageBodyWriterImpl {

	/**
	 * We Get the Below error, sometimes because JAX-RS expects some
	 * MessageBodyWriter for GET Request. It expects String and we have defined
	 * Types = DATE.
	 * 
	 * MessageBodyWriter not found for media type=text/plain, type=class
	 * java.util.Date, genericType=class java.util.Date.
	 * 
	 * 
	 * While creating a new mediatype you can pass multiple mediatype and check
	 * using Accept header params to get the particular output.
	 * 
	 * @return
	 */
	@GET
	@Produces({MediaType.TEXT_PLAIN, "text/shortdate"})		// use POSTMAN to test this by setting ACCEPT parameters from header Section.
	public Date getDate() {
		return Calendar.getInstance().getTime();
	}
}
