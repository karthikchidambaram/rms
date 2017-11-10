package com.i2g.rms.domain.model.admin;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.i2g.rms.domain.model.AbstractDataModel;

/**
 * Entity representation of Admin header statistics view.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 * @author RMS Development Team
 */
@Entity
@Table(name = "RMS_ADMIN_DASH_HDR_STAT_VW")
public class AdminDashboardHeaderStatistics extends AbstractDataModel<Long> implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private long _id;
	private long _totalIncidents;
	private long _draftCount;
	private long _openCount;
	private long _inProgressCount;
	private long _closedCount;	
	
	/**
	 * Default empty constructor required for Hibernate.
	 */
	protected AdminDashboardHeaderStatistics() {
	}	
	
	@Id
	@Column(name = "ID")
	@Override
	public Long getId() {
		return _id;
	}
	
	public void setId(final long id) {
		_id = id;
	}

	@Column(name = "TOTAL_INC_CNT")
	public long getTotalIncidents() {
		return _totalIncidents;
	}

	public void setTotalIncidents(final long totalIncidents) {
		_totalIncidents = totalIncidents;
	}
	
	@Column(name = "DRAFT_INC_CNT")
	public long getDraftCount() {
		return _draftCount;
	}

	public void setDraftCount(final long draftCount) {
		_draftCount = draftCount;
	}
	
	@Column(name = "NEW_INC_CNT")
	public long getOpenCount() {
		return _openCount;
	}

	public void setOpenCount(final long openCount) {
		_openCount = openCount;
	}
	
	@Column(name = "IN_PRGRS_INC_CNT")
	public long getInProgressCount() {
		return _inProgressCount;
	}

	public void setInProgressCount(final long inProgressCount) {
		_inProgressCount = inProgressCount;
	}
	
	@Column(name = "CLSD_INC_CNT")
	public long getClosedCount() {
		return _closedCount;
	}

	public void setClosedCount(final long closedCount) {
		_closedCount = closedCount;
	}	
}
