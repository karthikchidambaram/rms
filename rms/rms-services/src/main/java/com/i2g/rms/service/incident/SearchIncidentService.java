package com.i2g.rms.service.incident;

import java.util.List;
import java.util.Set;

import com.i2g.rms.domain.model.incident.SearchIncident;

/**
 * Service interface for search incident operation.
 * 
 * @since 1.0.0
 * @author Karthikeyan Chidambaram
 *
 */
public interface SearchIncidentService {

	public List<SearchIncident> get();
	public List<SearchIncident> get(final Set<String> loginIds, final boolean isAdmin);

}
