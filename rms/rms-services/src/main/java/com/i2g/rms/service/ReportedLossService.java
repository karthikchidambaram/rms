package com.i2g.rms.service;

import java.util.List;
import java.util.Set;

import com.i2g.rms.domain.model.ReportedLoss;

/**
 * Service interface for all reported loss related operations.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
public interface ReportedLossService {
	
	public List<ReportedLoss> get();
	
	public ReportedLoss get(final long id);
	
	public Set<ReportedLoss> createNewReportedLosses(final List<ReportedLoss> reportedLosses);
	
}
