package com.i2g.rms.rest.search;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation for defining whether an entity can be paginated on a REST 
 * controller endpoint (only useful for collections endpoints).
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 * @author RMS Development Team
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Inherited
@Documented
public @interface Pageable {
	
	/**
	 * Defines the entity class for which the paging is applied.
	 * 
	 * @return entity class for paging
	 */
	Class<?> value();
	
	/**
	 * Defines RO type for which paging is applied.
	 * 
	 * @return RO class for paging.
	 */
	Class<?> sourceType();
	
	/**
	 * Returns the default page size (number of records) to return when not 
	 * explicitly defined in request (defaults to 50).
	 * 
	 * @return default page size
	 */
	int defaultPageSize() default Integer.MAX_VALUE;
	
}
