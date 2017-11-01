package com.i2g.rms.persistence.dao;

import java.util.List;
import java.util.Set;

import com.i2g.rms.domain.model.Investigation;
import com.i2g.rms.domain.model.User;
import com.i2g.rms.domain.model.incident.Incident;

/**
 * Back-end DAO for Investigation related functions.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
public interface InvestigationDao {

	public List<Investigation> get();
	
	public List<Investigation> get(final User investigator);

	public Investigation get(final long id);
	
	public Investigation get(final Incident incident);
	
	public Investigation createInvestigation(final Investigation investigation);
	
	public Investigation updateInvestigation(final Investigation investigation);
	
	public void deleteInvestigation(final Investigation investigation);
	
	public void deleteInvestigations(final Set<Investigation> investigations);
	
	public boolean isInvestigatorAssigned(final long id);
}
