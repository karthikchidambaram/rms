package com.i2g.rms.persistence.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.i2g.rms.domain.model.Role;
import com.i2g.rms.persistence.hibernate.AbstractHibernateDao;

/**
 * Implementation class for RoleDao
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@Repository
public class RoleDaoImpl extends AbstractHibernateDao<Long, Role> implements RoleDao {

	private final Logger _logger = LoggerFactory.getLogger(RoleDaoImpl.class);

	@Autowired
	private HibernateTemplate _hibernateTemplate;

	/**
	 * Creates a new instance of {@link RoleDaoImpl}.
	 */
	private RoleDaoImpl() {
		super(Role.class);
	}

	public HibernateTemplate getHibernateTemplate() {
		return _hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		_hibernateTemplate = hibernateTemplate;
	}

	// Method to return all roles
	@SuppressWarnings("unchecked")
	@Override
	public List<Role> getRoles() {
		return (List<Role>) applySearch(getSession().createCriteria(_modelType)).list();
	}
}
