package org.harshal.javabrains.rest.config;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath(value = "webapi")
public class MyApp extends Application {

	/**
	 * Looking at JavaDocs for Application Class, there are 3 more methods that we
	 * can use in Application Class.
	 */

	/** 
	 * @see javax.ws.rs.core.Application#getClasses()
	 * 
	 * This method is used to fill all the methods in a set so that those classes will be used to trigger. 
	 * Classes not mentioned here will not be available to trigger/hit via URL.
	 */
	@Override
	public Set<Class<?>> getClasses() {
		
		Set<Class<?>> returnSet = new HashSet<>();
		
		// Converter
		returnSet.add(org.harshal.javabrains.rest.converter.DateExConverterProvider.class);
		
		// Filter
		returnSet.add(org.harshal.javabrains.rest.filter.LoggingFilter.class);
		returnSet.add(org.harshal.javabrains.rest.filter.PoweredByFilter.class);
		returnSet.add(org.harshal.javabrains.rest.filter.secure.BasicAuthFilter.class);
		
		// Message Body Writer
		returnSet.add(org.harshal.javabrains.rest.messageBodyWriter.DateMessageBodyWriter.class);
		returnSet.add(org.harshal.javabrains.rest.messageBodyWriter.ShortDateMessageBodyWriter.class);

		// Model
		returnSet.add(org.harshal.javabrains.rest.model.DateEx.class);
		
		// resources
		returnSet.add(org.harshal.javabrains.rest.resources.HelloWorld.class);
		returnSet.add(org.harshal.javabrains.rest.resources.MessageBodyWriterImpl.class);
		returnSet.add(org.harshal.javabrains.rest.resources.MyDate.class);
		returnSet.add(org.harshal.javabrains.rest.resources.MyResource.class);
		returnSet.add(org.harshal.javabrains.rest.resources.SecuredResource.class);
		
		// JWT AUTH implemented 
		returnSet.add(org.harshal.javabrains.jwt.filter.JwtTokenNeededFilter.class);
		returnSet.add(org.harshal.javabrains.jwt.hibernate.CRUDOperations.class);
		returnSet.add(org.harshal.javabrains.jwt.hibernate.HBConnection.class);
		returnSet.add(org.harshal.javabrains.jwt.model.JwtTokenNeeded.class);
		returnSet.add(org.harshal.javabrains.jwt.model.KeyModel.class);
		returnSet.add(org.harshal.javabrains.jwt.operations.KeyFunctions.class);
		returnSet.add(org.harshal.javabrains.jwt.resource.SomeEndpoints.class);
		returnSet.add(org.harshal.javabrains.jwt.resource.UserEndPoint.class);
		
		// Asymmetric Key 
		returnSet.add(org.harshal.javabrains.asymmetric.server.filter.AsymmetricRequestFilter.class);
		returnSet.add(org.harshal.javabrains.asymmetric.server.operations.AsymmetricServerOperation.class);
		returnSet.add(org.harshal.javabrains.asymmetric.server.model.PublicPrivate.class);
		returnSet.add(org.harshal.javabrains.asymmetric.server.resources.MyResources.class);
		returnSet.add(org.harshal.javabrains.asymmetric.server.utility.GenerateKeys.class);
		
		return returnSet;
	}

}
