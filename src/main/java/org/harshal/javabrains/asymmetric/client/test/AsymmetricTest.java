package org.harshal.javabrains.asymmetric.client.test;

import org.harshal.javabrains.asymmetric.client.operations.AsymmetricClientOperation;
import org.harshal.javabrains.asymmetric.server.operations.AsymmetricServerOperation;

public class AsymmetricTest {

	public static void main(String[] args) throws Exception {

		/*String baseURI = "http://localhost:8080/advanced-jaxrs/webapi/";
		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target(baseURI).path("asymmetric");
		Builder builder = webTarget.request();*/

		String value = null;

		AsymmetricClientOperation clientOps = new AsymmetricClientOperation();
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append("harshal.pande").append(":").append("harshal123");
		value = clientOps.getEncryptedText(stringBuffer.toString());
		
		System.out.println(value);
		
		AsymmetricServerOperation serverOps = new AsymmetricServerOperation();
		String decryptedText = serverOps.getDecryptedText(value);
		
		System.out.println(decryptedText);
		

		/*builder.header("CREDENTIALS", value);
		Response response = builder.get(Response.class);

		System.out.println(response.getStatus());
		System.out.println(response.getEntity());*/

	}

}
