package com.i2g.rms.persistence.dao;

import java.util.List;
import java.util.Set;

import com.i2g.rms.domain.model.ReportedLoss;

/**
 * Back-end DAO for Reported Loss related functions.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
public interface ReportedLossDao {	
	
	public List<ReportedLoss> get();
	
	public ReportedLoss get(final long id);
	
	public ReportedLoss createReportedLoss(final ReportedLoss reportedLoss);
	public List<ReportedLoss> createReportedLosses(final Set<ReportedLoss> reportedLosses);
	
	public ReportedLoss updateReportedLoss(final ReportedLoss reportedLoss);
	public List<ReportedLoss> updateReportedLosses(final Set<ReportedLoss> reportedLosses);
	
	public void deleteReportedLoss(final ReportedLoss reportedLoss);
	public void deleteReportedLosses(final Set<ReportedLoss> reportedLosses);
	
}
