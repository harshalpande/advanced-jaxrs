package org.harshal.javabrains.rest.converter;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Calendar;

import javax.ws.rs.ext.ParamConverter;
import javax.ws.rs.ext.ParamConverterProvider;
import javax.ws.rs.ext.Provider;

import org.harshal.javabrains.rest.model.DateEx;

@Provider
public class DateExConverterProvider implements ParamConverterProvider{

	@Override
	public <T> ParamConverter<T> getConverter(Class<T> rawType, Type genericType, Annotation[] listAnnotations) {
		if (rawType.getName().equals(DateEx.class.getName())) {
			return new ParamConverter<T>() {

				@Override
				public T fromString(String stringVal) {
					Calendar requestedDate = Calendar.getInstance();
					
					if (stringVal.equalsIgnoreCase("yesterday")) {
						requestedDate.add(Calendar.DATE, -1);
					}
					else if (stringVal.equalsIgnoreCase("tomorrow")) {
						requestedDate.add(Calendar.DATE, 1);
					}
					
					DateEx date = new DateEx();
					date.setDate(requestedDate.get(Calendar.DATE));
					date.setMonth(requestedDate.get(Calendar.MONTH));
					date.setYear(requestedDate.get(Calendar.YEAR));
					return rawType.cast(date);
				}

				@Override
				public String toString(T className) {
					if (className == null ) 
						return null;
					else 
						return className.toString();
				}
				
			};
		}
		
		return null;
	}

}
