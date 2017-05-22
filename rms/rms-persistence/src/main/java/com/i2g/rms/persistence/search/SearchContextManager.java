package com.i2g.rms.persistence.search;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public final class SearchContextManager {
	/** Thread local for storing the search contexts. */
	private static final ThreadLocal<EntitySearchContext> SEARCH_CONTEXTS = new ThreadLocal<>();

	/**
	 * Private constructor to prevent instantiation of static class.
	 */
	private SearchContextManager() {
	}

	/**
	 * Returns the search context bound to the current thread for the specified
	 * {@code entity} class or {@code null} if none is registered.
	 * 
	 * @param entity
	 *            to find search context for
	 * @return search context for entity or {@code null} if none registered
	 */
	public static SearchContext getContext(final Class<?> entity) {
		Objects.requireNonNull(entity, "Entity type cannot be null");
		final EntitySearchContext entityContext = SEARCH_CONTEXTS.get();
		return entityContext != null ? entityContext.getContext(entity) : null;
	}

	/**
	 * Sets the search {@code context} for the specified {@code entity} for the
	 * current thread.
	 * 
	 * @param entity
	 *            (non-null)
	 * @param context
	 *            (non-null)
	 */
	public static void setContext(final Class<?> entity, final SearchContext context) {
		Objects.requireNonNull(entity, "Entity type cannot be null");
		Objects.requireNonNull(context, "Search context cannot be null");
		EntitySearchContext c = SEARCH_CONTEXTS.get();
		if (c == null) {
			SEARCH_CONTEXTS.set(c = new EntitySearchContext());
		}
		c.addContext(entity, context);
	}

	/**
	 * Clears the search contexts registered with the current thread.
	 */
	public static void clear() {
		SEARCH_CONTEXTS.remove();
	}

	/**
	 * Container for storing multiple search contexts by entity class in the
	 * context manager's {@code ThreadLocal}.
	 */
	private final static class EntitySearchContext {

		/** Map of search contexts stored by entity class. */
		private final Map<Class<?>, SearchContext> _searchContexts = new LinkedHashMap<>();

		/**
		 * Adds the specified search {@code context} for the {@code entityType}.
		 * 
		 * @param entityType
		 * @param context
		 */
		void addContext(final Class<?> entityType, final SearchContext context) {
			_searchContexts.put(entityType, context);
		}

		/**
		 * Returns the search context for the specified {@code entityType}.
		 * 
		 * @param entityType
		 * @return search context or {@code null} if none defined
		 */
		SearchContext getContext(final Class<?> entityType) {
			return _searchContexts.get(entityType);
		}

	}
}
