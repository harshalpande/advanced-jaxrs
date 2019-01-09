package org.harshal.javabrains.client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

public class InvocationDemo {

	public static void main(String[] args) {

		InvocationDemo invocationDemo = new InvocationDemo();
		Invocation invocationGet = invocationDemo.getDateFromService();

		Response response = invocationGet.invoke();

		System.out.println(response.getMediaType());
		System.out.println(response.getDate());
	}

	/**
	 * Invocation is one of the best practices wherein you prepare the request via a
	 * method of type Invocation and then run the method by calling invoke() method
	 * over it to get the response as output.
	 * 
	 * @return
	 */
	private Invocation getDateFromService() {
		Client client = ClientBuilder.newClient();
		WebTarget webTargetBaseURI = client.target("http://localhost:8080/advanced-jaxrs/webapi");
		WebTarget testURI = webTargetBaseURI.path("messageBodyWriter");
		Builder builder = testURI.request("text/shortdate");
		Invocation invocation = builder.buildGet();

		return invocation;
	}

}
