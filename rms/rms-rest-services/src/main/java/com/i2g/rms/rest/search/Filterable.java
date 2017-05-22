package com.i2g.rms.rest.search;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation for defining whether an entity can be filtered in a search 
 * criteria for a REST controller endpoint.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 * @author RMS Development Team
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Inherited
@Documented
public @interface Filterable {
	
	/**
	 * Defines the entity class for which the filter is applied.
	 * 
	 * @return entity class for filter
	 */
	Class<?> value();
	
	/**
	 * Defines RO type for which filter is applied.
	 * 
	 * @return RO class for filter.
	 */
	Class<?> sourceType();
	
}
