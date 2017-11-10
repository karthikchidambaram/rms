package com.i2g.rms.domain.model.admin;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.i2g.rms.domain.model.AbstractDataModel;

/**
 * Entity representation of Admin dashboard accidental damage incident volume.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 * @author RMS Development Team
 */
@Entity
@Table(name = "RMS_ADMIN_ACCI_DMGE_VOL_VW")
public class AccidentDamageVolume extends AbstractDataModel<String> implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String _id;
	private long _incidentCount;
		
	/**
	 * Default empty constructor required for Hibernate.
	 */
	protected AccidentDamageVolume() {
	}	
	
	@Id
	@Column(name = "MONTH_YEAR")
	@Override
	public String getId() {
		return _id;
	}
	
	public void setId(final String id) {
		_id = id;
	}

	@Column(name = "ACCI_DMGE_COUNT")
	public long getIncidentCount() {
		return _incidentCount;
	}

	public void setIncidentCount(final long incidentCount) {
		_incidentCount = incidentCount;
	}
}
