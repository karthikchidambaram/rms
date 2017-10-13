package com.i2g.rms.rest.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ReportedLossWrapper {

	private String uniqueIncidentId;

	private List<ReportedLossRO> reportedLosses = new ArrayList<ReportedLossRO>(0);

	public String getUniqueIncidentId() {
		return uniqueIncidentId;
	}

	public void setUniqueIncidentId(final String uniqueIncidentId) {
		this.uniqueIncidentId = uniqueIncidentId;
	}

	public List<ReportedLossRO> getReportedLosses() {
		return reportedLosses;
	}

	public void setReportedLosses(final List<ReportedLossRO> reportedLosses) {
		this.reportedLosses = reportedLosses;
	}
}
