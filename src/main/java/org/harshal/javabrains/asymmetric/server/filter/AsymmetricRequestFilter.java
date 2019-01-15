package org.harshal.javabrains.asymmetric.server.filter;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.Provider;
import org.harshal.javabrains.asymmetric.server.model.PublicPrivate;
import org.harshal.javabrains.asymmetric.server.operations.AsymmetricServerOperation;

@Provider
@PublicPrivate
public class AsymmetricRequestFilter implements ContainerRequestFilter {

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		MultivaluedMap<String, String> mapHeader = requestContext.getHeaders();
		String credential = mapHeader.getFirst("CREDENTIAL");

		try {
			if (credential != null) {
				AsymmetricServerOperation serverOperation = new AsymmetricServerOperation();
				credential = serverOperation.getDecryptedText(credential);
				requestContext.setProperty("CREDENTIALS", credential);
			} else {
				Response response = Response.status(Status.BAD_REQUEST).entity("URL or TYPE is not found").build();
				requestContext.abortWith(response);
			}

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
