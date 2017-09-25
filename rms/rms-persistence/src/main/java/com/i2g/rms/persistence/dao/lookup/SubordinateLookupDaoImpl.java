package com.i2g.rms.persistence.dao.lookup;

import java.util.List;
import java.util.Objects;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.i2g.rms.domain.model.Subordinate;
import com.i2g.rms.persistence.hibernate.AbstractHibernateDao;

@Repository
public class SubordinateLookupDaoImpl extends AbstractHibernateDao<Long, Subordinate> implements SubordinateLookupDao {

	@Autowired
	private HibernateTemplate _hibernateTemplate;

	public SubordinateLookupDaoImpl() {
		super(Subordinate.class);
	}

	public HibernateTemplate getHibernateTemplate() {
		return _hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		_hibernateTemplate = hibernateTemplate;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Subordinate> get() {
		return (List<Subordinate>) applySearch(getSession().createCriteria(_modelType)).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Subordinate> get(final String managerLoginId) {
		final Criteria criteria = getSession().createCriteria(_modelType);
		criteria.add(Restrictions.eq("managerLoginId", Objects.requireNonNull(managerLoginId, "Manager Login Id cannot be null or empty.")));
		return (List<Subordinate>) applySearch(criteria).list();
	}
}
