package com.i2g.rms.rest.model.incident;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.i2g.rms.rest.model.ReportedLossRO;
import com.i2g.rms.rest.model.SuspectRO;
import com.i2g.rms.rest.model.UserRO;

/**
 * Search Incident Details RO Object
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class IncidentDetailRO extends BaseIncidentDetailRO {

	private Set<SuspectRO> existingSuspects = new HashSet<SuspectRO>(0);
	private Set<SuspectRO> newSuspects = new HashSet<SuspectRO>(0);
	private Set<UserRO> employeeSuspects = new HashSet<UserRO>(0);
	private Set<ReportedLossRO> reportedLosses = new HashSet<ReportedLossRO>(0);

	public Set<SuspectRO> getExistingSuspects() {
		return existingSuspects;
	}

	public void setExistingSuspects(final Set<SuspectRO> existingSuspects) {
		this.existingSuspects = existingSuspects;
	}

	public Set<SuspectRO> getNewSuspects() {
		return newSuspects;
	}

	public void setNewSuspects(final Set<SuspectRO> newSuspects) {
		this.newSuspects = newSuspects;
	}

	public Set<UserRO> getEmployeeSuspects() {
		return employeeSuspects;
	}

	public void setEmployeeSuspects(final Set<UserRO> employeeSuspects) {
		this.employeeSuspects = employeeSuspects;
	}

	public Set<ReportedLossRO> getReportedLosses() {
		return reportedLosses;
	}

	public void setReportedLosses(final Set<ReportedLossRO> reportedLosses) {
		this.reportedLosses = reportedLosses;
	}	
}
