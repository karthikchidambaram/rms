package com.i2g.rms.service.incident;

import java.util.List;

import com.i2g.rms.domain.model.incident.Incident;

/**
 * Service interface for all password history related operations.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
public interface IncidentService {
	
	public List<Incident> get();
	public Incident getIncidentByUniqueIncidentId(final String uniqueIncidentId);
	public Incident logIncident(final Incident incident);
	
}
