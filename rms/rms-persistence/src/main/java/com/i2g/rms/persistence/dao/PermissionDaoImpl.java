package com.i2g.rms.persistence.dao;

import java.util.List;
import java.util.Objects;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
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

	@Override
	public Permission getPermissionById(final long id) {
		final Criteria criteria = getSession().createCriteria(_modelType);
		criteria.add(Restrictions.eq("id", Objects.requireNonNull(id, "Permission id cannot be null or empty.")));
		return (Permission) criteria.uniqueResult();
	}

	@Override
	public Permission getPermissionByPermissionName(final String permissionName) {
		final Criteria criteria = getSession().createCriteria(_modelType);
		criteria.add(Restrictions.eq("permissionName", Objects.requireNonNull(permissionName, "Permission name cannot be null or empty.")));
		return (Permission) criteria.uniqueResult();
	}
}
