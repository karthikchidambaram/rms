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
	
	public Set<ReportedLoss> createNewReportedLosses(final List<ReportedLoss> reportedLosses);
	
}
