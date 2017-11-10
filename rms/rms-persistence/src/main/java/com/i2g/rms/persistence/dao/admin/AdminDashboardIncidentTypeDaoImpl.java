package com.i2g.rms.persistence.dao.admin;

import java.util.List;

import org.hibernate.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.i2g.rms.domain.model.admin.AdminDashboardIncidentType;
import com.i2g.rms.persistence.hibernate.AbstractHibernateDao;

/**
 * Implementation class for AdminDashboardIncidentTypeDao
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@Repository
public class AdminDashboardIncidentTypeDaoImpl extends AbstractHibernateDao<String, AdminDashboardIncidentType> implements AdminDashboardIncidentTypeDao {

	@Autowired
	private HibernateTemplate _hibernateTemplate;

	/**
	 * Creates a new instance of {@link AdminDashboardIncidentTypeDaoImpl}.
	 */
	private AdminDashboardIncidentTypeDaoImpl() {
		super(AdminDashboardIncidentType.class);
	}

	public HibernateTemplate getHibernateTemplate() {
		return _hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		_hibernateTemplate = hibernateTemplate;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AdminDashboardIncidentType> get() {
		final Criteria criteria = getSession().createCriteria(_modelType);
		return (List<AdminDashboardIncidentType>) criteria.list();
	}	
}
