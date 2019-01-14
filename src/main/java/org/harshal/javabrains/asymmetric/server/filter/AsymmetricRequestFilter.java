package org.harshal.javabrains.asymmetric.server.filter;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.Provider;
import javax.xml.ws.WebServiceContext;

import org.harshal.javabrains.asymmetric.server.model.PublicPrivate;
import org.harshal.javabrains.asymmetric.server.operations.AsymmetricCryptography;

@Provider
@PublicPrivate
public class AsymmetricRequestFilter implements ContainerRequestFilter{

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		MultivaluedMap<String, String> mapHeader = requestContext.getHeaders();
		String URL = mapHeader.getFirst("URL");
		String type = mapHeader.getFirst("TYPE");
		
		try {
			if (URL == null && type == null) {
				Response response = Response.status(Status.BAD_REQUEST).entity("URL or TYPE is not found").build();
				requestContext.abortWith(response);
			}
			AsymmetricCryptography asymmetricCryptography = new AsymmetricCryptography();
			URL = asymmetricCryptography
					.getDecryptedText(asymmetricCryptography.getPublicKey(AsymmetricCryptography.PUBLIC_KEY_PATH), URL);
			
			Client client = ClientBuilder.newClient();
			
			WebTarget webTarget = client.target(URL);
			
			requestContext.setRequestUri(webTarget.getUri());
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
