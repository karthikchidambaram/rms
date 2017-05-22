package com.i2g.rms.rest.search;

import java.lang.reflect.Method;

import java.util.Objects;

import org.springframework.core.annotation.AnnotationUtils;

/**
 * Bean for caching the search context annotation information associated with a
 * REST controller method.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 * @author RMS Development Team
 */
public class SearchMethodContext {
	
	/** Method to which the search context belongs. */
	private final Method _method;
	/** Entity type of the search context. */
	private final Class<?> _entityType;
	/** {@code true} if method supports paging. */
	private final boolean _pageable;
	/** {@code true} if method supports filtering. */
	private final boolean _filterable;
	/** {@code true} if method supports sorting. */
	private final boolean _sortable;
	/** Default paging size (if paging is supported). */
	private final int _defaultPageSize;
	/** Source type of the search context. */
	private final Class<?> _sourceType;
	
	/**
	 * Creates a new instance of {@code SearchMethodContext} for the specified
	 * {@code method}.
	 * 
	 * @param method to build context for
	 */
	SearchMethodContext(final Method method) {
		_method = Objects.requireNonNull(method, "Method cannot be null");
		
		final Searchable searchable = AnnotationUtils.getAnnotation(method, Searchable.class);
		final Pageable pageable = AnnotationUtils.getAnnotation(method, Pageable.class);
		final Filterable filterable = AnnotationUtils.getAnnotation(method, Filterable.class);
		final Sortable sortable = AnnotationUtils.getAnnotation(method, Sortable.class);
		
		_pageable = searchable != null || pageable != null;
		_filterable = searchable != null || filterable != null;
		_sortable = searchable != null || sortable != null;
		_defaultPageSize = searchable != null ? searchable.defaultPageSize() 
				: (pageable != null ? pageable.defaultPageSize() : -1);
		_entityType = getType(searchable, pageable, filterable, sortable);
		_sourceType = getSourceType(searchable,pageable, filterable, sortable);
	}
	
	/**
	 * Returns the entity type as defined by the specified annotations.
	 * 
	 * @param searchable
	 * @param pageable
	 * @param filterable
	 * @param sortable
	 * @return entity type defined in annotations
	 */
	private Class<?> getType(final Searchable searchable, final Pageable pageable, final Filterable filterable, final Sortable sortable) {
		if( searchable != null ) {
			return searchable.value();
		} else if( pageable != null ) {
			return pageable.value();
		} else if( filterable != null ) {
			return filterable.value();
		} else if( sortable != null ) {
			return sortable.value();
		}
		throw new IllegalStateException("Missing required entity type!");
	}
	
	/**
	 * Returns the source type as defined by the specified annotations.
	 * 
	 * @param searchable
	 * @param pageable
	 * @param filterable
	 * @param sortable
	 * @return source type defined in annotations.
	 */
	private Class<?> getSourceType(final Searchable searchable,final Pageable pageable, final Filterable filterable, final Sortable sortable) {
		if( searchable != null ) {
			return searchable.sourceType();
		} else if( pageable != null ) {
			return pageable.sourceType();
		} else if( filterable != null ) {
			return filterable.sourceType();
		} else if( sortable != null ) {
			return sortable.sourceType();
		}
		throw new IllegalStateException("Missing required source type!");
	}
	
	/**
	 * Returns the method to which the search context belongs.
	 * 
	 * @return method of search context
	 */
	public Method getMethod() {
		return _method;
	}
	
	/**
	 * Returns the entity type for which the search context applies.
	 * 
	 * @return entity type of search context
	 */
	public Class<?> getEntityType() {
		return _entityType;
	}
	
	/**
	 * Returns {@code true} if method supports paging.
	 * 
	 * @return true if paging is supported
	 */
	public boolean isPageable() {
		return _pageable;
	}
	
	/**
	 * Returns {@code true} if method supports filtering.
	 * 
	 * @return true if filtering is supported
	 */
	public boolean isFilterable() {
		return _filterable;
	}
	
	/**
	 * Returns {@code true} if method supports sorting.
	 * 
	 * @return true if sorting is supported
	 */
	public boolean isSortable() {
		return _sortable;
	}
	
	/**
	 * Returns the default page size.
	 * 
	 * @return default page size
	 */
	public int getDefaultPageSize() {
		return _defaultPageSize;
	}
	
	/**
	 * Returns source type.
	 * @return source type of search context.
	 */
	public Class<?> getSourceClass() {
		return _sourceType;
	}
	
	@Override
	public String toString() {
		return "SearchMethodContext method: " + _method
				+ " entityType: " + _entityType
				+ " pageable: " + _pageable
				+ " filterable: " + _filterable
				+ " sortable: " + _sortable;
	}
	
}
