package org.harshal.javabrains.rest.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * There are 2 types of application lifecycles :
 * 1) Non-Singleton - which doesn't save the state of any class and serves 
 * 		new object of class when asked for
 * 2) @Singleton - which saves the state of the object (here count variable)
 * 		Until the server is running like below.
 * 
 * Classes Marked as Singleton are created before a URL is hit/ Resource is accessed.
 * 
 * So in the below example, if we are annotating PathParam, QueryParam and other 
 * parameters to Member variables, while starting server we get ModelValidationException, 
 * because Class marked as Singleton needs value before a resource is hit/accessed. Hence 
 * We can't marked this class as singleton.
 * 
 * 
 */

@Path("{ppVal}/myResource")
/*@Singleton*/
public class MyResource {

	/*private int count;*/
	
	/**
	 * Method level annotation can also be done like below only to make these values available for other 
	 * methods if declared as well.
	 * 
	 * NOTE: class can't be declared as Singleton.
	 * 
	 * */
	
	@PathParam("ppVal") private String pathParamVar;
	@QueryParam("qpVal") private String queryParamVar;

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String testMethod() {
		/*count++;
		return "It works!! This method was called " + count + " time(s).";*/
		return "PathParam value is :" + pathParamVar + " QueryParam value is :" + queryParamVar;
	}

}
