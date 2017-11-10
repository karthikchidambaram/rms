package com.i2g.rms.rest.model.admin;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.i2g.rms.rest.model.AbstractEntityRO;

/**
 * REST Object for Admin dashboard header statistics RO.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 * @author RMS Development Team
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AdminDashboardHeaderStatisticsRO extends AbstractEntityRO {

	private long _id;
	private long _totalIncidents;
	private long _draftCount;
	private long _openCount;
	private long _inProgressCount;
	private long _closedCount;
	
	public long getId() {
		return _id;
	}

	public void setId(final long id) {
		_id = id;
	}

	public long getTotalIncidents() {
		return _totalIncidents;
	}

	public void setTotalIncidents(final long totalIncidents) {
		_totalIncidents = totalIncidents;
	}

	public long getDraftCount() {
		return _draftCount;
	}

	public void setDraftCount(final long draftCount) {
		_draftCount = draftCount;
	}

	public long getOpenCount() {
		return _openCount;
	}

	public void setOpenCount(final long openCount) {
		_openCount = openCount;
	}

	public long getInProgressCount() {
		return _inProgressCount;
	}

	public void setInProgressCount(final long inProgressCount) {
		_inProgressCount = inProgressCount;
	}

	public long getClosedCount() {
		return _closedCount;
	}

	public void setClosedCount(final long closedCount) {
		_closedCount = closedCount;
	}
}
