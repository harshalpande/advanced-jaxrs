package org.harshal.javabrains.jwt.model;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.ws.rs.NameBinding;

/**
 * Create a new Annotation of name @JwtTokenNeeded.
 * 
 * Methods Annotated with this token and ContainerRequestfilter annotated with
 * this annotation then the filter will be able to work only for those methods
 * that are annotated.
 * 
 * @author pandehar
 *
 */
@NameBinding
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.METHOD })
public @interface JwtTokenNeeded {

}
