package org.harshal.javabrains.asymmetric.server.resources;

import java.util.StringTokenizer;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.harshal.javabrains.asymmetric.server.model.PublicPrivate;
import org.harshal.javabrains.jwt.hibernate.CRUDOperations;
import org.harshal.javabrains.jwt.model.KeyModel;

@Path("asymmetric")
public class MyResources {

	CRUDOperations operations = new CRUDOperations();
	
	/**
	 * @param request
	 * @return
	 */
	@GET
	@PublicPrivate
	public Response getAsymmetricParseURl(@Context ContainerRequestContext/* HttpServletRequest */ request) {
		Object attribute = request.getProperty("CREDENTIALS");
		String credentaials = String.valueOf(attribute);
		StringTokenizer tokenizer = new StringTokenizer(credentaials, ":");
		String user = tokenizer.nextToken();

		if (user != null) {
			KeyModel objectKeyModel = operations.fetchOperation(user);
			if (objectKeyModel != null) {
				StringBuilder stringBuilder = new StringBuilder();
				stringBuilder.append("USER-NAME=").append(objectKeyModel.getUser());
				// stringBuilder.append("\n");
				stringBuilder.append(", KEY=").append(objectKeyModel.getKey());
				// stringBuilder.append("\n");
				stringBuilder.append(", JWT-Token=").append(objectKeyModel.getToken());
				return Response.status(Status.OK).entity(stringBuilder.toString()).build();
			}
		}
		return Response.status(Status.NOT_FOUND).entity("Data Not found").build();
	}

}
