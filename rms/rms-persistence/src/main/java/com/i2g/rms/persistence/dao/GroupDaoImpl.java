package com.i2g.rms.persistence.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.i2g.rms.domain.model.Group;
import com.i2g.rms.persistence.hibernate.AbstractHibernateDao;

/**
 * Implementation class for GroupDao
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@Repository
public class GroupDaoImpl extends AbstractHibernateDao<Long, Group> implements GroupDao {

	private final Logger _logger = LoggerFactory.getLogger(GroupDaoImpl.class);

	@Autowired
	private HibernateTemplate _hibernateTemplate;

	/**
	 * Creates a new instance of {@link GroupDaoImpl}.
	 */
	private GroupDaoImpl() {
		super(Group.class);
	}

	public HibernateTemplate getHibernateTemplate() {
		return _hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		_hibernateTemplate = hibernateTemplate;
	}

	// Method to return all groups
	@SuppressWarnings("deprecation")
	@Override
	public List<Group> getGroups() {
		return (List<Group>) applySearch(getSession().createCriteria(_modelType)).list();
	}
}
