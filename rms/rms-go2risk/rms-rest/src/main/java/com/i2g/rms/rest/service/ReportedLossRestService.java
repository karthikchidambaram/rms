package com.i2g.rms.rest.service;

import java.util.List;

import com.i2g.rms.domain.model.ReportedLoss;
import com.i2g.rms.domain.model.incident.Incident;
import com.i2g.rms.rest.model.DeleteRO;
import com.i2g.rms.rest.model.ReportedLossRO;
import com.i2g.rms.rest.model.ReportedLossWrapper;

/**
 * Rest Service Interface for role rest services.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
public interface ReportedLossRestService {

	public List<ReportedLossRO> get();

	public ReportedLossRO get(final long id);

	public ReportedLossRO createReportedLoss(final String uniqueIncidentId, final ReportedLossRO reportedLossRO);

	public List<ReportedLossRO> createReportedLosses(final ReportedLossWrapper reportedLossWrapper);

	public ReportedLossRO updateReportedLoss(final ReportedLossRO reportedLossRO);

	public List<ReportedLossRO> updateReportedLosses(final ReportedLossWrapper reportedLossWrapper);
	
	public void deleteReportedLoss(final Long reportedLossId);

	public void deleteReportedLosses(final DeleteRO deleteRO);

	public ReportedLoss constructNewReportedLoss(final Incident incident, final ReportedLossRO reportedLossRO);

	public ReportedLoss constructReportedLossForUpdate(final ReportedLossRO reportedLossRO);

}
