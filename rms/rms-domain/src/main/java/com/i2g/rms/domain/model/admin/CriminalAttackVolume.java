package com.i2g.rms.domain.model.admin;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.i2g.rms.domain.model.AbstractDataModel;

/**
 * Entity representation of Admin dashboard criminal attack incident volume.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 * @author RMS Development Team
 */
@Entity
@Table(name = "RMS_ADMIN_CRIM_ATTK_VOL_VW")
public class CriminalAttackVolume extends AbstractDataModel<String> implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String _id;
	private long _incidentCount;
		
	/**
	 * Default empty constructor required for Hibernate.
	 */
	protected CriminalAttackVolume() {
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

	@Column(name = "CRIM_ATTK_COUNT")
	public long getIncidentCount() {
		return _incidentCount;
	}

	public void setIncidentCount(final long incidentCount) {
		_incidentCount = incidentCount;
	}
	
	@Override
	public boolean equals(final Object obj) {
		if (obj == this) {
			return true;
		} else if (obj instanceof CriminalAttackVolume) {
			final CriminalAttackVolume other = (CriminalAttackVolume) obj; 
			return Objects.equals(_id, other._id); 
		}
		return false;
	}

	@Override
	public int hashCode() {
		int hash = 5;
		hash = 43 * hash + Objects.hashCode(_id);
		return hash;
	}

	@Override
	public String toString() {
		return "Month & Year: " + _id + ", Count: " + _incidentCount;
	}
}
