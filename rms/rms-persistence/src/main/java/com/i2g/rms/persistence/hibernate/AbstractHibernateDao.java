package com.i2g.rms.persistence.hibernate;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.transform.BasicTransformerAdapter;
import org.hibernate.transform.ResultTransformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.i2g.rms.domain.model.AbstractDataModel;
import com.i2g.rms.persistence.search.PageCriteria;
import com.i2g.rms.persistence.search.SearchContext;
import com.i2g.rms.persistence.search.SearchContextManager;
import com.i2g.rms.persistence.search.SearchService;

public abstract class AbstractHibernateDao<K extends Serializable, T extends AbstractDataModel<K>> {

	/** {@code Logger} instance. */
	protected final Logger _logger = LoggerFactory.getLogger(getClass());
	/** Class type of {@link AbstractDataModel} implementation. */
	protected final Class<T> _modelType;
	/** Hibernate session factory for executing Hibernate queries. */
	@Autowired
	protected SessionFactory _sessionFactory;
	/** Service for building dynamic search criteria. */
	@Autowired
	protected SearchService _searchService;

	/**
	 * Creates a new instance of {@link AbstractHibernateDao} for the specified
	 * {@code modelType} of {@link AbstractDataModel}.
	 * 
	 * @param modelType
	 *            of {@code AbstractDataModel}
	 */
	protected AbstractHibernateDao(final Class<T> modelType) {
		if (modelType == null) {
			throw new IllegalArgumentException("Model type cannot be null");
		}
		_modelType = modelType;
	}

	/**
	 * Returns the entity instance matching the specified {@code id} or
	 * {@code null} if no matching entity is found.
	 * 
	 * @param id
	 *            of entity to retrieve
	 * @return entity instance or {@code null} if not found
	 */
	public T get(final K id) {
		if (id == null) {
			throw new IllegalArgumentException(
					"Failed to retrieve " + _modelType.getSimpleName() + ", id cannot be null");
		}
		return (T) getSession().get(_modelType, id);
	}

	/**
	 * Returns the current Hibernate session for executing queries.
	 * 
	 * @return current session
	 */
	protected final Session getSession() {
		return _sessionFactory.getCurrentSession();
	}

	/**
	 * Saves the specified {@code entity} instance and returns the generated ID.
	 * 
	 * @param entity
	 *            to save/create
	 * @return generated ID of saved entity
	 */
	public K save(final T entity) {
		if (entity == null) {
			throw new IllegalArgumentException(
					"Failed to save " + _modelType.getSimpleName() + ", entity cannot be null");
		}
		return (K) getSession().save(entity);
	}

	/**
	 * Updates the specified {@code entity}.
	 * 
	 * @param entity
	 *            to update
	 */
	public void update(final T entity) {
		if (entity == null) {
			throw new IllegalArgumentException(
					"Failed to update " + _modelType.getSimpleName() + ", entity cannot be null");
		}
		getSession().update(entity);
	}

	/**
	 * Deletes the specified {@code entity}.
	 * 
	 * @param entity
	 *            to delete
	 */
	public void delete(final T entity) {
		if (entity == null) {
			throw new IllegalArgumentException(
					"Failed to delete " + _modelType.getSimpleName() + ", entity cannot be null");
		}
		getSession().delete(entity);
	}

	/**
	 * Applies any filters, sorts and paging specified in the search context
	 * bound to this thread for this entity to the specified {@code criteria}.
	 * If no sorts are defined in the search context, then the
	 * {@code defaultOrders} are applied to the criteria.
	 * 
	 * @param criteria
	 *            to updated with search criteria
	 * @param defaultOrders
	 *            to apply if no sorts are defined
	 * @return updated criteria with search applied
	 */
	protected final Criteria applySearch(final Criteria criteria, final Order... defaultOrders) {
		final SearchContext searchContext = SearchContextManager.getContext(_modelType);
		if (searchContext == null) {
			if (defaultOrders != null) {
				for (final Order defaultOrder : defaultOrders) {
					criteria.addOrder(defaultOrder);
				}
			}
			return criteria;
		}
		_searchService.applyFilters(criteria, searchContext);
		_searchService.applyPaging(criteria, searchContext);
		_searchService.applySorts(criteria, searchContext, defaultOrders);

		return criteria;
	}

	/**
	 * Applies paging to the specified {@code query} by setting the first result
	 * and max results attributes of the query based on any pagination defined
	 * in the search context bound to the current thread.
	 * 
	 * @param query
	 *            to update with paging
	 * @return updated query
	 */
	protected final Query applyPaging(final Query query) {
		final SearchContext searchContext = SearchContextManager.getContext(_modelType);
		if (searchContext == null || searchContext.getPageCriteria() == null) {
			return query;
		}

		// Apply paging criteria to the query instance
		final PageCriteria pageCriteria = searchContext.getPageCriteria();
		query.setFirstResult(pageCriteria.getCurrentPage() * pageCriteria.getPageSize());
		query.setMaxResults(searchContext.getPageCriteria().getPageSize());
		return query;
	}

	/**
	 * Returns a new {@code ResultTransformer} instance which converts a list of
	 * {@code Object[]} instances to a list of type {@code T} by pulling out the
	 * entity from the tuple using the specified {@code entityIndex}. This is a
	 * helper method for named SQL queries which return multiple entities based
	 * on a join and returns a list of the primary parent entity.
	 * 
	 * @param entityIndex
	 *            in Object[] tuples
	 * @return list of entities of type {@code T}
	 */
	protected ResultTransformer createTransformer(final int entityIndex) {
		return new BasicTransformerAdapter() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public List<T> transformList(final List collection) {
				return ((List<Object[]>) collection).stream().map((o) -> (T) o[entityIndex])
						.collect(Collectors.toList());
			}
		};
	}

	/**
	 * Method that gives the current timestamp
	 * 
	 * @return
	 */
	protected LocalDateTime getCurrentTimestamp() {
		return LocalDateTime.now();
	}

	/**
	 * Method which validates {@code object} for table maintenance
	 * entities.
	 * 
	 * @param object
	 * @throws IllegalArgumentException
	 *             if the input object is null or empty.
	 */
	protected void validateObject(final Object object) {
		if (object == null) {
			throw new IllegalArgumentException("Object is null or empty.");
		}
	}

	/**
	 * Method which validates the input parameter {@code code} for table
	 * maintenance entities.
	 * 
	 * @param code
	 * @throws IllegalArgumentException
	 *             if the code is null or emtpy.
	 */
	protected void validateCode(final String code) {
		if (code == null || code.trim().isEmpty()) {
			throw new IllegalArgumentException("Code cannot be null or emtpy.");
		}
	}

	/**
	 * Method which validates the input parameter {@description description} for
	 * table maintenance entities.
	 * 
	 * @param description
	 * @throws IllegalArgumentException
	 *             if the description is null or emtpy.
	 */
	protected void validateDescription(final String description) {
		if (description == null || description.trim().isEmpty()) {
			throw new IllegalArgumentException("Description cannot be null or emtpy.");
		}
	}
}
