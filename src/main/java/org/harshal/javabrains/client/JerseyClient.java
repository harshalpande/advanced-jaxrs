package org.harshal.javabrains.client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

public class JerseyClient {

	public static void main(String[] args) {
		
		Client client = ClientBuilder.newClient();
		
		WebTarget webTargetBaseURI = client.target("http://localhost:8080/advanced-jaxrs/webapi");
		
		WebTarget testURI = webTargetBaseURI.path("messageBodyWriter");
		
		//Builder builder = testURI.request("text/plain");		// output - Thu Jan 03 00:14:35 IST 2019 - Normal Date
		Builder builder = testURI.request("text/shortdate");	// output - 3-1-2019 - Short Date via Custom Created MediaType
		
		//Response response = builder.get();
		Response response = builder.get();
		
		String responseString = builder.get(String.class);  
		// for Unmarshalling we can directly set here the POJO Class name of type of
		// Object expected. However we can always pass String.class to get the response
		// in String format.
		
		System.out.println(response.getMediaType());
		System.out.println(response.getCookies());
		System.out.println(response.getStatus());
		System.out.println(response.getLocation());
		System.out.println(responseString);
	}
}
