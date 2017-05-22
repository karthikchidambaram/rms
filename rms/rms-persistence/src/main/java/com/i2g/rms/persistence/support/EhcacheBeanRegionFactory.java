package com.i2g.rms.persistence.support;

import java.util.Properties;

import org.hibernate.boot.spi.SessionFactoryOptions;
import org.hibernate.cache.CacheException;
import org.hibernate.cache.ehcache.EhCacheRegionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.util.Assert;

import net.sf.ehcache.CacheManager;

/**
 * Implementation of {@link EhCacheRegionFactory} to use an existing Ehcache
 * {@link CacheManager} for second level caching of entities.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 * @author RMS Development Team
 */
public class EhcacheBeanRegionFactory extends EhCacheRegionFactory {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/** {@code Logger} instance. */
	private final Logger _logger = LoggerFactory.getLogger(EhcacheBeanRegionFactory.class);
	
	/**
	 * Creates a new instance of {@link EhcacheBeanRegionFactory}.
	 */
	public EhcacheBeanRegionFactory() {
		super(null);
	}
	
	@Override
	public void start(final SessionFactoryOptions settings, final Properties properties) throws CacheException {
		Assert.notNull(manager, "CacheManager cannot be null");
		_logger.debug("Initializing EhcacheBeanRegionFactory with CacheManager [{}]", manager.getName());
		this.settings = settings;
		mbeanRegistrationHelper.registerMBean(manager, properties);
	}
	
	/**
	 * Sets the specified Hibernate {@code cacheManager} to use for caching
	 * entities in a second level cache.
	 * 
	 * @param cacheManager 
	 */
	@Required
	public void setCacheManager(final CacheManager cacheManager) {
		this.manager = cacheManager;
	}

}
