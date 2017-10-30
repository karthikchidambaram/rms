package com.i2g.rms.rest.model.wrapper;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.i2g.rms.rest.model.ReportedLossRO;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ReportedLossWrapper {

	private String uniqueIncidentId;
	private Set<ReportedLossRO> reportedLosses = new HashSet<ReportedLossRO>(0);

	public String getUniqueIncidentId() {
		return uniqueIncidentId;
	}

	public void setUniqueIncidentId(final String uniqueIncidentId) {
		this.uniqueIncidentId = uniqueIncidentId;
	}

	public Set<ReportedLossRO> getReportedLosses() {
		return reportedLosses;
	}

	public void setReportedLosses(final Set<ReportedLossRO> reportedLosses) {
		this.reportedLosses = reportedLosses;
	}
}
