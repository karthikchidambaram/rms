package com.i2g.rms.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.i2g.rms.domain.model.ReportedLoss;
import com.i2g.rms.persistence.dao.ReportedLossDao;

/**
 * Back-end service for reported loss related functions.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@Service
public class ReportedLossServiceImpl extends AbstractService implements ReportedLossService {

	@Autowired
	private ReportedLossDao _reportedLossDao;

	private ReportedLossServiceImpl() {
	}

	@Override
	public List<ReportedLoss> get() {
		return _reportedLossDao.get();
	}

	@Override
	public ReportedLoss get(final long id) {
		return _reportedLossDao.get(id);
	}

	@Override
	public ReportedLoss createReportedLoss(final ReportedLoss reportedLoss) {
		return _reportedLossDao.createReportedLoss(reportedLoss);
	}

	@Override
	public List<ReportedLoss> createReportedLosses(final Set<ReportedLoss> reportedLosses) {
		return _reportedLossDao.createReportedLosses(reportedLosses);
	}

	@Override
	public ReportedLoss updateReportedLoss(final ReportedLoss reportedLoss) {
		return _reportedLossDao.updateReportedLoss(reportedLoss);
	}

	@Override
	public List<ReportedLoss> updateReportedLosses(final Set<ReportedLoss> reportedLosses) {
		return _reportedLossDao.updateReportedLosses(reportedLosses);
	}

	@Override
	public void deleteReportedLoss(final ReportedLoss reportedLoss) {
		_reportedLossDao.deleteReportedLoss(reportedLoss);		
	}

	@Override
	public void deleteReportedLosses(final Set<ReportedLoss> reportedLosses) {
		_reportedLossDao.deleteReportedLosses(reportedLosses);
	}
}
