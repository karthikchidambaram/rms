package com.i2g.rms.persistence.dao.incident;

import java.util.List;

import com.i2g.rms.domain.model.incident.Incident;

public interface IncidentDao {
	
	public List<Incident> get();
	public Incident getIncidentByUniqueIncidentId(final String uniqueIncidentId);
	
}
