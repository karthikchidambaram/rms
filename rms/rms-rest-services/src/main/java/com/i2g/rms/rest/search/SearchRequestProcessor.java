package com.i2g.rms.rest.search;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.dozer.metadata.ClassMappingMetadata;
import org.dozer.metadata.FieldMappingMetadata;
import org.dozer.metadata.MappingMetadata;
import org.dozer.metadata.MetadataLookupException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.i2g.rms.domain.model.AbstractDataModel;
import com.i2g.rms.persistence.search.FilterCriteria;
import com.i2g.rms.persistence.search.PageCriteria;
import com.i2g.rms.persistence.search.SearchContext;
import com.i2g.rms.persistence.search.SortCriteria;
import com.i2g.rms.rest.model.AbstractEntityRO;
import com.i2g.rms.rest.search.model.FilterRO;
import com.i2g.rms.rest.search.model.SearchRO;
import com.i2g.rms.rest.search.model.SortRO;

/**
 * Service for retrieving the search information from the HTTP request header of
 * a REST controller method endpoint.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 * @author RMS Development Team
 */
@Component
public class SearchRequestProcessor {
	
	/** Constant HTTP request header {@value} specifying search criteria. */
	public final static String SEARCH_REQUEST_HEADER = "Search";
	
	/** {@code Logger} instance. */
	private final Logger _logger = LoggerFactory.getLogger(SearchRequestProcessor.class);
	
	/** Jackson JSON mapper for parsing search criteria. */
	@Autowired
	private ObjectMapper _mapper;
	
	/** Dozer Mapper to convert RO/entity objects.*/
	@Autowired
	private Mapper _modelMapper;
	
	
	/**
	 * Parses the search context from the HTTP servlet {@code request} for the
	 * specified {@code methodContext}.
	 * 
	 * @param request to retrieve search criteria header
	 * @param methodContext defining allowable search criteria
	 * @return parsed search context
	 */
	public SearchContext parse(final HttpServletRequest request, final SearchMethodContext methodContext) {
		// First pull the search criteria from the request header
		final SearchRO search = getSearchCriteria(request);
		
		//entitydata
		Map<String,Object> entityData = new HashMap<>();
		Map<String, String> fieldMappings = new HashMap<>();
		
		// source target  mappings
		if (search != null && (!search.getFilters().isEmpty() || !search.getSorts().isEmpty())) {
			fieldMappings = getFieldMappings(methodContext.getSourceClass(), methodContext.getEntityType());	
		}
		
		if (search != null && !search.getFilters().isEmpty()) {
			
			final Map<String,String> searchFilters = getFilterData(search.getFilters());
			//sourcedata
			final Map<String,Object> sourceData = getSourceData(searchFilters, methodContext.getSourceClass());
			
			// entity key and source object.
			final Map<String,Object> convertedData = updateSourceKeys(sourceData, fieldMappings);
			entityData = convertEntityData(convertedData, methodContext.getEntityType());
		}
		
		// Build and return search context from request/defaults
		final SearchContext.Builder builder = new SearchContext.Builder();
		addPaging(builder, search, methodContext);
		addFilters(builder, search, methodContext, entityData, fieldMappings);
		addSorts(builder, search, methodContext, fieldMappings);
		return builder.build();
	}
	
	/**
	 * Adds any paging criteria from the {@code searchRequest} to the context
	 * {@code builder} as defined in the method {@code context}.
	 * 
	 * @param builder to apply paging to
	 * @param searchRequest to pull paging request
	 * @param context of method defining if paging allowed and default paging
	 * @return updated builder with paging criteria
	 */
	private SearchContext.Builder addPaging(final SearchContext.Builder builder, final SearchRO searchRequest, final SearchMethodContext context) {
		if( !context.isPageable() ) {
			if( searchRequest != null && searchRequest.getPaging() != null ) {
				_logger.warn("REST method [{}] does not support Pageable; received paging criteria in search request", context.getMethod());
			}
			return builder;	// Nothing to build since paging not supported
		} else if( searchRequest != null && searchRequest.getPaging() != null ) {
			return builder.setPageCriteria(new PageCriteria(searchRequest.getPaging().getCurrentPage(), searchRequest.getPaging().getPageSize()));
		}
		return context.getDefaultPageSize() != Integer.MAX_VALUE
				? builder.setPageCriteria(new PageCriteria(0, context.getDefaultPageSize()))
				: builder;
	}
	
	/**
	 * Adds any filter criteria from the {@code searchRequest} to the context
	 * {@code builder} as defined in the method {@code context}.
	 * 
	 * @param builder to add filter criteria to
	 * @param searchRequest to pull filter criteria from
	 * @param context of method defining if filtering is allowed
	 * @param entityData map of entity key and value as Object 
	 * @param fieldMappings map of sourceType key and entityType value 
	 * @return updated builder with filter criteria
	 */
	private SearchContext.Builder addFilters(final SearchContext.Builder builder, final SearchRO searchRequest, final SearchMethodContext context, Map<String, Object> entityData, Map<String, String> fieldMappings) {
		if( searchRequest == null ) {
			return builder;	// No filters to apply if no search request
		} else if( !context.isFilterable() ) {
			if( !searchRequest.getFilters().isEmpty() ) {
				_logger.warn("REST method [{}] does not support Filterable; received filter criteria in search request", context.getMethod());
			}
			return builder;	// Nothing to build since filtering not supported
		}
		
		for( final FilterRO filter : searchRequest.getFilters() ) {
			String fieldName = filter.getField();
			
			// if there is change in field name between sourceType and entityType, update fieldName. 
			if(fieldMappings.get(filter.getField()) != null) {
				fieldName = fieldMappings.get(filter.getField());
			}
			builder.addFilterCriteria(new FilterCriteria(fieldName, filter.getOperator(), entityData.get(fieldName)));
		}
		return builder;
	}
	
	/**
	 * Adds any sort criteria from the {@code searchRequest} to the context
	 * {@code builder} as defined in the method {@code context}.
	 * 
	 * @param builder to add sort criteria to
	 * @param searchRequest to pull sort criteria from
	 * @param context of method defining if sorting is allowed
	 * @param fieldMappings map of sourceType key and entityType value  
	 * @return updated builder with sort criteria
	 */
	private SearchContext.Builder addSorts(final SearchContext.Builder builder, final SearchRO searchRequest, final SearchMethodContext context, final Map<String, String> fieldMappings) {
		if( searchRequest == null ) {
			return builder;	// No sorts to apply if no search request
		} else if( !context.isSortable()) {
			if( !searchRequest.getSorts().isEmpty() ) {
				_logger.warn("REST method [{}] does not support Sortable; received sort criteria in search request", context.getMethod());
			}
			return builder;	// Nothing to build since filtering not supported
		}
		for (final SortRO sort : searchRequest.getSorts()) {
			String field = sort.getField();
			if (fieldMappings.get(sort.getField()) != null) {
				field = fieldMappings.get(sort.getField());
			}
			builder.addSortCriteria(new SortCriteria(field, sort.getOrder()));
		}
		return builder;
	}
	
	/**
	 * Returns the de-serialized {@code SearchRO} parsed from the specified HTTP
	 * servlet {@code request}.
	 * 
	 * @param request to retrieve search criteria header
	 * @return de-serialized search criteria
	 */
	private SearchRO getSearchCriteria(final HttpServletRequest request) {
		// Retrieve and parse search header
		final String searchHeader = request.getHeader(SEARCH_REQUEST_HEADER);
		if( searchHeader == null ) {
			return null;
		}
		try {
			return _mapper.readValue(searchHeader, SearchRO.class);
		} catch(Exception e) {
			throw new IllegalArgumentException("Invalid search header: " + searchHeader, e);
		}
	}
	
	
	
		/**
		 * Returns map of filter field and values.
		 * 
		 * @param filters search parameters.
		 * @return map of filter field and value.
		 */
		private Map<String,String> getFilterData(final List<FilterRO> filters) {
			final Map<String, String> filterData = new HashMap<>();
			for (final FilterRO filterRO : filters) {
				filterData.put(filterRO.getField(), filterRO.getValue());
			}
			return filterData;
		}
		
		//TODO <p> Note: <p>if source Key is having protected setter method, building map will fail.
		/**
		 * converts filterData in to sorurceObject and sourceObject to Map.
		 * 
		 * @param filterData
		 * @param sourceType
		 * @return map of sourceData as key as source field name and value as Object.
		 */
		private Map<String,Object> getSourceData(final Map<String,String> filterData, final Class<?> sourceType) {
			final Map<String, Object> sourceData = new HashMap<>();
			try {
				final Object sourceObject = _mapper.convertValue(filterData, sourceType);
				for (final Map.Entry<String, String> filter : filterData.entrySet()) {
					sourceData.put(filter.getKey(), new PropertyDescriptor(filter.getKey(), sourceType).getReadMethod().invoke(sourceObject));
				}
			} catch (IllegalArgumentException |  InvocationTargetException | IntrospectionException  | IllegalAccessException e) {
				throw new IllegalArgumentException("Issue while converting object to map : ", e);
			} 
			return sourceData;
		}
		
		/**
		 * Returns updated sourceData, as key of entityType and value as Object of sourceType.
		 * 
		 * @param sourceData of sourceObject Key and value
		 * @param fieldMappings
		 * @return map of key as entityType and value as Object of sourceType. 
		 */
		private Map<String,Object> updateSourceKeys(final Map<String,Object> sourceData, final Map<String,String> fieldMappings) {
			final Map<String,Object> entityData = new HashMap<>();
			for (final Map.Entry<String, Object> sourceKeyValue : sourceData.entrySet()) {
				String key = sourceKeyValue.getKey();
				if(fieldMappings.get(sourceKeyValue.getKey()) != null) {
					key = fieldMappings.get(sourceKeyValue.getKey());
				}
				entityData.put(key, sourceKeyValue.getValue());			
			}
			return entityData;
		}
		
		/**
		 * Returns entityData by converting convertedData.
		 *  
		 * @param convertedData as key of entityType field and value of sourceType Object.
		 * @param targetType 
		 * @return entityData
		 */
		private Map<String,Object> convertEntityData(final Map<String,Object> convertedData, final Class<?> targetType) {
			Object targetObject = _mapper.convertValue(convertedData, targetType);
			return getKeyValues(targetObject, targetType);
		}
		
		/**
		 * Converts Object in to Map.
		 * 
		 * @param sourceObject
		 * @param type
		 * @return converted object as Map.
		 */
		private Map<String,Object> getKeyValues(final Object sourceObject, final Class<?> type) {
			final Map<String,Object> entityData = new HashMap<>();
			 try {
				for(final PropertyDescriptor propertyDescriptor : Introspector.getBeanInfo(type).getPropertyDescriptors()) {
		            entityData.put(propertyDescriptor.getName(), propertyDescriptor.getReadMethod().invoke(sourceObject));
				}
			} catch (IntrospectionException |IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				throw new IllegalArgumentException("Issue while converting object to map : ", e);
			} 
			 return entityData;
		}
		
		/**
		 * Returns Map key as sourceType field and value as entityType field.
		 * 
		 * @param sourceType
		 * @param entityType
		 * @return map of sourceType and entityType fields.
		 */
		private  Map<String, String> getFieldMappings(final Class<?> sourceType, final Class<?> entityType) {
			final DozerBeanMapper beanMapper =(DozerBeanMapper) _modelMapper;
			final MappingMetadata mappingMetadata =  beanMapper.getMappingMetadata();
			final Map<String, String> fieldMappings = new HashMap<>();
			
			try {
				//TODO update variable names in search method context to source and target ? instead of entityType.
				final ClassMappingMetadata classMappingMetadata = mappingMetadata.getClassMapping(sourceType,entityType);
				setMappings(classMappingMetadata, fieldMappings);
			} catch (MetadataLookupException e) {
				_logger.debug("No mapping xml found from :" + sourceType + "to :", entityType);
			}
			
			try {
				if(entityType.getSuperclass().isAssignableFrom(AbstractDataModel.class)) {
					setMappings(mappingMetadata.getClassMapping(AbstractEntityRO.class, AbstractDataModel.class), fieldMappings);
				}
			} catch (MetadataLookupException e) {
				_logger.debug("No mapping xml found from : AbstractDataModel.class to : AbstractEntityRO.class");
			}

			return fieldMappings;
		}
		
		private void setMappings(final ClassMappingMetadata classMappingMetadata, final Map<String, String> fieldMappings) {
			for(final FieldMappingMetadata fieldMappingMetadata : classMappingMetadata.getFieldMappings()) {
				fieldMappings.put(fieldMappingMetadata.getSourceName(), fieldMappingMetadata.getDestinationName());
			}
		}

	
}
