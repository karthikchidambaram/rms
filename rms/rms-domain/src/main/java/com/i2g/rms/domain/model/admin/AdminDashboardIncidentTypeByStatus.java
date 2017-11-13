package com.i2g.rms.domain.model.admin;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.i2g.rms.domain.model.AbstractDataModel;

/**
 * Entity representation of Admin dashboard incident type.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 * @author RMS Development Team
 */
@Entity
@Table(name = "RMS_ADMIN_INC_TYP_BY_STS_VW")
public class AdminDashboardIncidentTypeByStatus extends AbstractDataModel<Long> implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private long _id;
	private String _incidentType;
	private String _incidentStatus;
	private long _incidentCount;
		
	/**
	 * Default empty constructor required for Hibernate.
	 */
	protected AdminDashboardIncidentTypeByStatus() {
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

	@Column(name = "INC_COUNT")
	public long getIncidentCount() {
		return _incidentCount;
	}

	public void setIncidentCount(final long incidentCount) {
		_incidentCount = incidentCount;
	}
	
	@Column(name = "INC_STATUS")
	public String getIncidentStatus() {
		return _incidentStatus;
	}

	public void setIncidentStatus(final String incidentStatus) {
		_incidentStatus = incidentStatus;
	}
	
	@Column(name = "INC_TYPE")
	public String getIncidentType() {
		return _incidentType;
	}

	public void setIncidentType(final String incidentType) {
		_incidentType = incidentType;
	}	
}
