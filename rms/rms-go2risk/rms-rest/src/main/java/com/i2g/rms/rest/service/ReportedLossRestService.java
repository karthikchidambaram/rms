package com.i2g.rms.rest.service;

import java.util.List;
import java.util.Set;

import com.i2g.rms.domain.model.ReportedLoss;
import com.i2g.rms.domain.model.incident.Incident;
import com.i2g.rms.rest.model.DeleteRO;
import com.i2g.rms.rest.model.ReportedLossRO;
import com.i2g.rms.rest.model.wrapper.ReportedLossWrapper;

/**
 * Rest Service Interface for reported loss rest services.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
public interface ReportedLossRestService {

	public List<ReportedLossRO> get();
	
	public Set<ReportedLossRO> getReportedLossesByIncidentId(final Long incidentId);
	
	public Set<ReportedLossRO> getReportedLossesByUniqueIncidentId(final String uniqueIncidentId);
	
	public List<ReportedLossRO> getReportedLossTableByIncidentId(final Long incidentId);
	
	public List<ReportedLossRO> getReportedLossTableByUniqueIncidentId(final String uniqueIncidentId);

	public ReportedLossRO get(final long reportedLossId);

	public ReportedLossRO createReportedLoss(final ReportedLossRO reportedLossRO);
	
	public ReportedLossRO createReportedLossForIncidentId(final Long incidentId, final ReportedLossRO reportedLossRO);
	
	public ReportedLossRO createReportedLossForUniqueIncidentId(final String uniqueIncidentId, final ReportedLossRO reportedLossRO);

	public List<ReportedLossRO> createReportedLosses(final ReportedLossWrapper reportedLossWrapper);

	public ReportedLossRO updateReportedLoss(final ReportedLossRO reportedLossRO);

	public List<ReportedLossRO> updateReportedLosses(final ReportedLossWrapper reportedLossWrapper);

	public void deleteReportedLoss(final Long reportedLossId);

	public void deleteReportedLosses(final DeleteRO deleteRO);

	public ReportedLoss constructNewReportedLoss(final Incident incident, final ReportedLossRO reportedLossRO);

	public ReportedLoss constructReportedLoss(final ReportedLossRO reportedLossRO);

	public ReportedLoss setOtherFieldsForReportedLoss(final ReportedLoss reportedLoss, final ReportedLossRO reportedLossRO);

}
