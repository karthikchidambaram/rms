package com.i2g.rms.persistence.dao.admin;

import java.util.List;

import org.hibernate.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.i2g.rms.domain.model.admin.AssetDamageVolume;
import com.i2g.rms.persistence.hibernate.AbstractHibernateDao;

/**
 * Implementation class for asset damage incident volume dao.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@Repository
public class AssetDamageVolumeDaoImpl extends AbstractHibernateDao<String, AssetDamageVolume> implements AssetDamageVolumeDao {

	@Autowired
	private HibernateTemplate _hibernateTemplate;

	/**
	 * Creates a new instance of {@link AssetDamageVolumeDaoImpl}.
	 */
	private AssetDamageVolumeDaoImpl() {
		super(AssetDamageVolume.class);
	}

	public HibernateTemplate getHibernateTemplate() {
		return _hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		_hibernateTemplate = hibernateTemplate;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AssetDamageVolume> get() {
		final Criteria criteria = getSession().createCriteria(_modelType);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return (List<AssetDamageVolume>) criteria.list();
	}	
}
