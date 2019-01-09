package org.harshal.javabrains.rest.messageBodyWriter;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Date;

import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

@Provider
@Produces(MediaType.TEXT_PLAIN)
public class DateMessageBodyWriter implements MessageBodyWriter<Date> {

	/**
	 * DEPRECATED METHOD in JAX-RS 2.0 onwards - return -1
	 */
	@Override
	public long getSize(Date arg0, Class<?> arg1, Type arg2, Annotation[] arg3, MediaType arg4) {
		return -1;
	}

	/**
	 * This method is of Boolean Return type, we just have to check the type and
	 * return @true or @false, if we are able to send response depeding on type.
	 */
	@Override
	public boolean isWriteable(Class<?> classType, Type arg1, Annotation[] listAnnotations, MediaType mediaType) {
		return Date.class.isAssignableFrom(classType);
		
		/**
		 * check the @isAssignableFrom method. This just checks whether the given class
		 * can be converted to DestinationClass Type
		 */
	}

	@Override
	public void writeTo(Date date, Class<?> classType, Type type, Annotation[] listAnnotation, MediaType mediaType,
			MultivaluedMap<String, Object> map, OutputStream os) throws IOException, WebApplicationException {
		
		os.write(date.toString().getBytes());
	
	}
}
