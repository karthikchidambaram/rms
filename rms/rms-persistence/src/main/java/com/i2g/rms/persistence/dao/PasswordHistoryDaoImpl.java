package com.i2g.rms.persistence.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.i2g.rms.domain.model.PasswordHistory;
import com.i2g.rms.persistence.hibernate.AbstractHibernateDao;

/**
 * Implementation class for PasswordHistoryDao
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@Repository
public class PasswordHistoryDaoImpl extends AbstractHibernateDao<Long, PasswordHistory> implements PasswordHistoryDao {

	private final Logger _logger = LoggerFactory.getLogger(PasswordHistoryDaoImpl.class);

	@Autowired
	private HibernateTemplate _hibernateTemplate;

	/**
	 * Creates a new instance of {@link PasswordHistoryDaoImpl}.
	 */
	private PasswordHistoryDaoImpl() {
		super(PasswordHistory.class);
	}

	public HibernateTemplate getHibernateTemplate() {
		return _hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		_hibernateTemplate = hibernateTemplate;
	}

	// Method to return all password history
	@SuppressWarnings("deprecation")
	@Override
	public List<PasswordHistory> getPasswordHistory() {
		return (List<PasswordHistory>) applySearch(getSession().createCriteria(_modelType)).list();
	}
}
