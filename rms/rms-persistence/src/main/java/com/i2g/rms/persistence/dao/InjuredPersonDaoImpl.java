package com.i2g.rms.persistence.dao;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.i2g.rms.domain.model.InjuredPerson;
import com.i2g.rms.domain.model.StatusFlag;
import com.i2g.rms.persistence.hibernate.AbstractHibernateDao;

/**
 * Implementation class for InjuredPersonDao
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@Repository
public class InjuredPersonDaoImpl extends AbstractHibernateDao<Long, InjuredPerson> implements InjuredPersonDao {

	@SuppressWarnings("unused")
	private final Logger _logger = LoggerFactory.getLogger(InjuredPersonDaoImpl.class);

	@Autowired
	private HibernateTemplate _hibernateTemplate;

	/**
	 * Creates a new instance of {@link InjuredPersonDaoImpl}.
	 */
	private InjuredPersonDaoImpl() {
		super(InjuredPerson.class);
	}

	public HibernateTemplate getHibernateTemplate() {
		return _hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		_hibernateTemplate = hibernateTemplate;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<InjuredPerson> get() {
		final Criteria criteria = getSession().createCriteria(_modelType);
		criteria.add(Restrictions.eq("statusFlag", StatusFlag.ACTIVE));
		return (List<InjuredPerson>) criteria.list();
	}
	
	@Override
	public InjuredPerson get(final long id) {
		final Criteria criteria = getSession().createCriteria(_modelType);
		criteria.add(Restrictions.eq("id", Objects.requireNonNull(id, "Injured person id cannot be null or empty.")));
		criteria.add(Restrictions.eq("statusFlag", StatusFlag.ACTIVE));
		return (InjuredPerson) criteria.uniqueResult();
	}

	@Override
	/**
	 * While creating new Injured Persons, there will be no unique id representing the records uniquely.
	 * So a list will be used. But after saving the records the return collection can be a set.
	 */	
	public Set<InjuredPerson> createNewInjuredPersons(final List<InjuredPerson> injuredPersons) {
		validateCollectionObject(injuredPersons);
		final Set<InjuredPerson> newInjuredPersons = new HashSet<InjuredPerson>(0);
		for (InjuredPerson injuredPerson : injuredPersons) {
			final Long id = save(injuredPerson);
			if (id != null) {
				newInjuredPersons.add(get(id));
			}
		}
		return newInjuredPersons;
	}
}
