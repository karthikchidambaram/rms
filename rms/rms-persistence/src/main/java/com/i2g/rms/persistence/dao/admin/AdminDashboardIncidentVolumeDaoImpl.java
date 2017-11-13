package com.i2g.rms.persistence.dao.admin;

import java.util.List;

import org.hibernate.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.i2g.rms.domain.model.admin.AdminDashboardIncidentVolume;
import com.i2g.rms.persistence.hibernate.AbstractHibernateDao;

/**
 * Implementation class for AdminDashboardIncidentVolumeDao
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@Repository
public class AdminDashboardIncidentVolumeDaoImpl extends AbstractHibernateDao<String, AdminDashboardIncidentVolume> implements AdminDashboardIncidentVolumeDao {

	@Autowired
	private HibernateTemplate _hibernateTemplate;

	/**
	 * Creates a new instance of {@link AdminDashboardIncidentVolumeDaoImpl}.
	 */
	private AdminDashboardIncidentVolumeDaoImpl() {
		super(AdminDashboardIncidentVolume.class);
	}

	public HibernateTemplate getHibernateTemplate() {
		return _hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		_hibernateTemplate = hibernateTemplate;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AdminDashboardIncidentVolume> get() {
		final Criteria criteria = getSession().createCriteria(_modelType);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return (List<AdminDashboardIncidentVolume>) criteria.list();
	}	
}
