package org.harshal.javabrains.client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;

public class GenericTypeDemo {
	
	public static void main(String[] args) {
		
		// Hit sample REST api
		// https://samples.openweathermap.org/data/2.5/find?lat=55.5&lon=37.5&cnt=10&appid=b
		
		Client client = ClientBuilder.newClient();

		WebTarget baseWebTarget = client.target("https://samples.openweathermap.org/data/2.5/find");

		WebTarget targetURL = baseWebTarget.queryParam("lat", "55.5").queryParam("lon", "37.5").queryParam("cnt", "10")
				.queryParam("appid", "fsdf");

		Builder builder = targetURL.request();
		
		String response = builder.get(new GenericType<String>() {} );
		
		System.out.println(response);
		
	}

}
