package com.i2g.rms.persistence.dao.admin;

import org.hibernate.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.i2g.rms.domain.model.admin.AdminDashboardHeaderStatistics;
import com.i2g.rms.persistence.hibernate.AbstractHibernateDao;

/**
 * Implementation class for AdminDashboardHeaderStatisticsDao
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@Repository
public class AdminDashboardHeaderStatisticsDaoImpl extends AbstractHibernateDao<Long, AdminDashboardHeaderStatistics> implements AdminDashboardHeaderStatisticsDao {

	@Autowired
	private HibernateTemplate _hibernateTemplate;

	/**
	 * Creates a new instance of {@link AdminDashboardHeaderStatisticsDaoImpl}.
	 */
	private AdminDashboardHeaderStatisticsDaoImpl() {
		super(AdminDashboardHeaderStatistics.class);
	}

	public HibernateTemplate getHibernateTemplate() {
		return _hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		_hibernateTemplate = hibernateTemplate;
	}

	@SuppressWarnings("unchecked")
	@Override
	public AdminDashboardHeaderStatistics get() {
		final Criteria criteria = getSession().createCriteria(_modelType);
		return (AdminDashboardHeaderStatistics) criteria.uniqueResult();
	}	
}
