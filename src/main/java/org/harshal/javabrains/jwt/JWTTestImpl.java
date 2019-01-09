package org.harshal.javabrains.jwt;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class JWTTestImpl {
	
	public static void main(String[] args) {
		String baseUri = "http://localhost:8080/advanced-jaxrs/webapi/";
		final String QUERY_PARAM_MESSAGE = "message";

		Client client = ClientBuilder.newClient();
		//GET : http://localhost:8080/advanced-jaxrs/webapi/echo-endpoints/jwt?message=harshalpande
		WebTarget baseWebTarget = client.target(baseUri);
		WebTarget path = baseWebTarget.path("echo-endpoints").path("jwt");

		WebTarget finalPath = path.queryParam(QUERY_PARAM_MESSAGE, "harshalpande");
		
		Builder requestBuilder = finalPath.request();
		
		Response response = requestBuilder.get();
		
		int status = response.getStatus();
		String responseReason = response.getStatusInfo().getReasonPhrase();
		
		System.out.println(status + ":" + responseReason);
		
		String readEntity = response.readEntity(String.class);
		
		System.out.println(readEntity);
		
		if (status == 403 && "Forbidden".equalsIgnoreCase(responseReason)) {
			
			// POST : http://localhost:8080/advanced-jaxrs/webapi/users/login
			WebTarget postPath = baseWebTarget.path("users").path("login");
			
			Form form = new Form();
			form.param("userId", "harshal.pande");
			form.param("password", "harshal123");
			
			Builder postBuilder = postPath.request()
					.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED);

			//postBuilder.
			
			Response postResponse = postBuilder.post(Entity.form(form));
			
			status = postResponse.getStatus();
			responseReason = postResponse.getStatusInfo().getReasonPhrase();
			
			System.out.println(status + ":" + responseReason);
			readEntity = postResponse.readEntity(String.class);
			
			System.out.println(readEntity);
			
			String jwtoken = postResponse.getHeaderString(HttpHeaders.AUTHORIZATION);
			System.out.println(jwtoken);
			if (jwtoken != null) {
				Builder builder = finalPath.request().header(HttpHeaders.AUTHORIZATION, jwtoken);
				Response responseGetAgain = builder.get();
				status = responseGetAgain.getStatus();
				responseReason = responseGetAgain.getStatusInfo().getReasonPhrase();
				System.out.println(status + ":" + responseReason);
				readEntity = responseGetAgain.readEntity(String.class);
				System.out.println(readEntity);
			}
			
		}
	}

}
