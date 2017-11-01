package com.i2g.rms.rest.model.wrapper;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.i2g.rms.rest.model.ReportedLossRO;
import com.i2g.rms.rest.model.incident.BaseIncidentDetailRO;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ReportedLossWrapper extends BaseIncidentDetailRO {

	private Set<ReportedLossRO> reportedLosses = new HashSet<ReportedLossRO>(0);

	public Set<ReportedLossRO> getReportedLosses() {
		return reportedLosses;
	}

	public void setReportedLosses(final Set<ReportedLossRO> reportedLosses) {
		this.reportedLosses = reportedLosses;
	}
}
