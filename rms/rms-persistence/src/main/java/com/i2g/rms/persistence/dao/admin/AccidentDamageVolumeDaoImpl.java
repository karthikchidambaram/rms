package com.i2g.rms.persistence.dao.admin;

import java.util.List;

import org.hibernate.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.i2g.rms.domain.model.admin.AccidentDamageVolume;
import com.i2g.rms.persistence.hibernate.AbstractHibernateDao;

/**
 * Implementation class for Accident damage incident volume dao.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@Repository
public class AccidentDamageVolumeDaoImpl extends AbstractHibernateDao<String, AccidentDamageVolume> implements AccidentDamageVolumeDao {

	@Autowired
	private HibernateTemplate _hibernateTemplate;

	/**
	 * Creates a new instance of {@link AccidentDamageVolumeDaoImpl}.
	 */
	private AccidentDamageVolumeDaoImpl() {
		super(AccidentDamageVolume.class);
	}

	public HibernateTemplate getHibernateTemplate() {
		return _hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		_hibernateTemplate = hibernateTemplate;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AccidentDamageVolume> get() {
		final Criteria criteria = getSession().createCriteria(_modelType);
		return (List<AccidentDamageVolume>) criteria.list();
	}	
}
