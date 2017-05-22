package com.i2g.rms.rest.search;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.hibernate.boot.model.source.spi.Sortable;
import org.hibernate.mapping.Filterable;

/**
 * Annotation for defining whether a specific entity can be searched for a REST
 * controller method, including filtering, sorting and paging.  Using this
 * annotation is the equivalent of adding {@link Filterable}, {@link Sortable}
 * and {@code Pageable} annotations.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 * @author RMS Development Team
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Inherited
@Documented
public @interface Searchable {
	
	/**
	 * Defines the entity class for which the search is applied.
	 * 
	 * @return entity class for search
	 */
	Class<?> value();
	
	/**
	 * Defines RO type for which search is applied.
	 * 
	 * @return RO class for search.
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
