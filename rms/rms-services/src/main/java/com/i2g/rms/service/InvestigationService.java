package com.i2g.rms.service;

import java.util.List;

import com.i2g.rms.domain.model.Investigation;
import com.i2g.rms.domain.model.incident.Incident;

/**
 * Service interface for all Investigation related operations.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
public interface InvestigationService {
	
	public List<Investigation> get();
	
	public Investigation get(final long id);
	
	public Investigation create(final Investigation investigation);
	
	public Investigation updateInvestigation(final Investigation investigation);
	
	public Investigation get(final Incident incident);
	
	public boolean isInvestigatorAssigned(final long id);
}
