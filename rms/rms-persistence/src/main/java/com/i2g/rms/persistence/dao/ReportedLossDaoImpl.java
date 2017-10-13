package com.i2g.rms.persistence.dao;

import java.util.ArrayList;
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

import com.i2g.rms.domain.model.ReportedLoss;
import com.i2g.rms.domain.model.StatusFlag;
import com.i2g.rms.persistence.hibernate.AbstractHibernateDao;

/**
 * Implementation class for ReportedLossDao
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@Repository
public class ReportedLossDaoImpl extends AbstractHibernateDao<Long, ReportedLoss> implements ReportedLossDao {

	@SuppressWarnings("unused")
	private final Logger _logger = LoggerFactory.getLogger(ReportedLossDaoImpl.class);

	@Autowired
	private HibernateTemplate _hibernateTemplate;

	/**
	 * Creates a new instance of {@link ReportedLossDaoImpl}.
	 */
	private ReportedLossDaoImpl() {
		super(ReportedLoss.class);
	}

	public HibernateTemplate getHibernateTemplate() {
		return _hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		_hibernateTemplate = hibernateTemplate;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ReportedLoss> get() {
		final Criteria criteria = getSession().createCriteria(_modelType);
		criteria.add(Restrictions.eq("statusFlag", StatusFlag.ACTIVE));
		return (List<ReportedLoss>) criteria.list();
	}
	
	@Override
	public ReportedLoss get(final long id) {
		final Criteria criteria = getSession().createCriteria(_modelType);
		criteria.add(Restrictions.eq("id", Objects.requireNonNull(id, "Reported loss id cannot be null or empty.")));
		criteria.add(Restrictions.eq("statusFlag", StatusFlag.ACTIVE));
		return (ReportedLoss) criteria.uniqueResult();
	}
	
	@Override
	public ReportedLoss createReportedLoss(final ReportedLoss reportedLoss) {
		validateObject(reportedLoss);
		final Long id = save(reportedLoss);
		if (id != null) {
			return get(id);
		} else {
			return null;
		}
	}

	@Override
	public List<ReportedLoss> createReportedLosses(final Set<ReportedLoss> reportedLosses) {
		validateCollectionObject(reportedLosses);
		final List<ReportedLoss> newReportedLosses = new ArrayList<ReportedLoss>(0);
		for (ReportedLoss reportedLoss : reportedLosses) {
			final Long id = save(reportedLoss);
			if (id != null) {
				newReportedLosses.add(get(id));
			}
		}
		return newReportedLosses;
	}

	@Override
	public ReportedLoss updateReportedLoss(final ReportedLoss reportedLoss) {
		validateObject(reportedLoss);
		final Long id = save(reportedLoss);
		if (id != null) {
			return get(id);
		} else {
			return null;
		}
	}
	
	@Override
	public List<ReportedLoss> updateReportedLosses(final Set<ReportedLoss> reportedLosses) {
		validateCollectionObject(reportedLosses);
		final List<ReportedLoss> updatedReportedLosses = new ArrayList<ReportedLoss>(0);
		for (ReportedLoss reportedLoss : reportedLosses) {
			final Long id = save(reportedLoss);
			if (id != null) {
				updatedReportedLosses.add(get(id));
			}
		}
		return updatedReportedLosses;
	}

	@Override
	public void deleteReportedLoss(final ReportedLoss reportedLoss) {
		if (reportedLoss != null) {
			delete(reportedLoss);
		}
	}

	@Override
	public void deleteReportedLosses(final Set<ReportedLoss> reportedLosses) {
		if (reportedLosses != null && !reportedLosses.isEmpty()) {
			for (ReportedLoss reportedLoss : reportedLosses) {
				if (reportedLoss != null) {
					deleteReportedLoss(reportedLoss);
				}
			}
		}
	}
}
