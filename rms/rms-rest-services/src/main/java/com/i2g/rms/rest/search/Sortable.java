package com.i2g.rms.rest.search;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation for defining whether a specific entity can be sorted for a REST
 * controller method.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 * @author RMS Development Team
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Inherited
@Documented
public @interface Sortable {
	
	/**
	 * Defines the entity class for which the sort is applied.
	 * 
	 * @return entity class for sort
	 */
	Class<?> value();
	
	/**
	 * Defines RO type for which sort is applied.
	 * 
	 * @return RO class for sort.
	 */
	Class<?> sourceType();
	
}
