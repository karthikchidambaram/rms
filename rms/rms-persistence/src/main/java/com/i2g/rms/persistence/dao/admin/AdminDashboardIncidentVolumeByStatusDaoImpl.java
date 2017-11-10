package com.i2g.rms.persistence.dao.admin;

import java.util.List;

import org.hibernate.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.i2g.rms.domain.model.admin.AdminDashboardIncidentVolumeByStatus;
import com.i2g.rms.persistence.hibernate.AbstractHibernateDao;

/**
 * Implementation class for AdminDashboardIncidentVolumeByStatusDao
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@Repository
public class AdminDashboardIncidentVolumeByStatusDaoImpl extends AbstractHibernateDao<String, AdminDashboardIncidentVolumeByStatus> implements AdminDashboardIncidentVolumeByStatusDao {

	@Autowired
	private HibernateTemplate _hibernateTemplate;

	/**
	 * Creates a new instance of
	 * {@link AdminDashboardIncidentVolumeByStatusDaoImpl}.
	 */
	private AdminDashboardIncidentVolumeByStatusDaoImpl() {
		super(AdminDashboardIncidentVolumeByStatus.class);
	}

	public HibernateTemplate getHibernateTemplate() {
		return _hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		_hibernateTemplate = hibernateTemplate;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AdminDashboardIncidentVolumeByStatus> get() {
		final Criteria criteria = getSession().createCriteria(_modelType);
		return (List<AdminDashboardIncidentVolumeByStatus>) criteria.list();
	}
}
