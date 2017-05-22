package com.i2g.rms.persistence.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.i2g.rms.domain.model.Permission;
import com.i2g.rms.persistence.hibernate.AbstractHibernateDao;

/**
 * Implementation class for PermissionDao
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@Repository
public class PermissionDaoImpl extends AbstractHibernateDao<Long, Permission> implements PermissionDao {

	private final Logger _logger = LoggerFactory.getLogger(PermissionDaoImpl.class);

	@Autowired
	private HibernateTemplate _hibernateTemplate;

	/**
	 * Creates a new instance of {@link PermissionDaoImpl}.
	 */
	private PermissionDaoImpl() {
		super(Permission.class);
	}

	public HibernateTemplate getHibernateTemplate() {
		return _hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		_hibernateTemplate = hibernateTemplate;
	}

	// Method to return all permissions
	@SuppressWarnings("deprecation")
	@Override
	public List<Permission> getPermissions() {
		return (List<Permission>) applySearch(getSession().createCriteria(_modelType)).list();
	}
}
